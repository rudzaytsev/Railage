package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.RouteDao;
import com.tsystems.jschool.railage.datasource.TrainDao;
import com.tsystems.jschool.railage.datasource.TrainRideDao;
import com.tsystems.jschool.railage.domain.Period;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.Train;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.exceptions.*;
import com.tsystems.jschool.railage.view.controllers.helpers.FindRidesFormParams;
import com.tsystems.jschool.railage.view.controllers.helpers.TimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by rudolph on 28.06.15.
 */
@Service
@Transactional(readOnly = true)
public class TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainRideDao trainRideDao;

    @Autowired
    private RouteDao routeDao;


    private static final String TRAIN_NUMBER_PATTERN = "^[0-9a-zA-Z]+";

    public static final int HOURS_UPPER_BOUND = 23;

    public static final int HOURS_LOWER_BOUND = 0;

    public static final int MINUTES_UPPER_BOUND = 59;

    public static final int MINUTES_LOWER_BOUND = 0;

    private static final String ADDITION_SECONDS = ":00";


    public List<Train> findAllTrains(){
        return trainDao.findAll();
    }


    public List<TrainRide> findAllRidesByTrainId(Integer id){

        Train train;
        List<TrainRide> rides = null;
        train = trainDao.findById(id);
        if(train != null) {
            rides = train.getRides();
        }
        return rides;
    }

    public Train findTrainById(Integer id){
        return trainDao.findById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void addTrain(String trainNumber,Integer seats)
            throws NotPositiveNumberOfSeatsException,DomainObjectAlreadyExistsException,
            IncorrectParameterException {

        if (seats <= 0) {
            throw new NotPositiveNumberOfSeatsException(
                    "Number of seats should be positive");
        }
        if (!trainNumber.matches(TRAIN_NUMBER_PATTERN)) {
            throw new IncorrectParameterException(
                    "Train number should contains only latin characters and digits");
        }
        List<Train> trains = trainDao.findTrainsByTrainNumber(trainNumber);
        if (!trains.isEmpty()) {
            throw new DomainObjectAlreadyExistsException(
                    "Train with such parameters already exists");
        }
        trainDao.persist(new Train(seats, trainNumber));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void addTrainRide(Integer routeId, String dateStr, String priceStr) throws ParseException, TimeTableConflictException, InvalidPriceException {

        Route route = routeDao.findById(routeId);
        Train train = route.getTrain();
        java.sql.Date date = this.validateDate(dateStr,route.getPeriod());
        Integer price = this.validatePrice(priceStr);
        TrainRide ride = new TrainRide(route,date,train,price);
        trainRideDao.merge(ride);
    }

    private Integer validatePrice(String priceStr) throws NumberFormatException, InvalidPriceException {
        int price = Integer.parseInt(priceStr);
        if(price <= 0 ){
            throw new InvalidPriceException("Ride's price should be possitive");
        }
        return price;
    }

    private java.sql.Date validateDate(String dateStr, String period) throws ParseException, TimeTableConflictException {

        // using for validation
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateFormat.parse(dateStr));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (period.equals(Period.WEEKDAYS.value())){
            boolean isWeekDay = (dayOfWeek != Calendar.SATURDAY)
                                    && (dayOfWeek != Calendar.SUNDAY);
            if (!isWeekDay){
               throw new TimeTableConflictException(
                    "Ride conflicts with timetable. " +
                    "Ride date should be weekday if train route period is WEEKDAYS."
               );
            }
        }
        else if (period.equals(Period.WEEKENDS.value())){
            boolean isWeekEnd = (dayOfWeek == Calendar.SATURDAY)
                    || (dayOfWeek == Calendar.SUNDAY);

            if (!isWeekEnd){
                throw new TimeTableConflictException(
                        "Ride conflicts with timetable." +
                        "Ride date should be weekend if train route period is WEEKENDS.");
            }
        }

        java.sql.Date dateSql = java.sql.Date.valueOf(dateStr);
        return dateSql;
    }


    public Train merge(Train train){
        return trainDao.merge(train);
    }


    public List<TrainRide> findAllTrainRides(){
        return trainRideDao.findAll();
    }


    public TrainRide findTrainRideById(Integer id){
        return trainRideDao.findById(id);
    }

    public List<TrainRide> findRidesBy(Integer sourceStationId, Integer destStationId,
                                       TimeInterval interval) {

        return trainRideDao.findRidesBy(sourceStationId,destStationId,interval);
    }


    private void validateTime(String time) throws IncorrectTimeFormatException {

        String[] hoursAndMinutes = time.split(":",2);
        if (hoursAndMinutes.length < 2){
            throw new IncorrectTimeFormatException(
                    "Incorrect Format of time. Should be hh:mm .Example 18:30");
        }
        try {
            int hours = Integer.parseInt(hoursAndMinutes[0]);
            int minutes = Integer.parseInt(hoursAndMinutes[1]);

            if (hours > HOURS_UPPER_BOUND || hours < HOURS_LOWER_BOUND){
                throw new IncorrectTimeFormatException(
                        "Incorrect Format of time. Hours must be in range from 0 to 23");
            }
            if (minutes > MINUTES_UPPER_BOUND || minutes < MINUTES_LOWER_BOUND ){
                throw new IncorrectTimeFormatException(
                        "Incorrect Format of time. Minutes must be in range from 0 to 59");
            }
        }
        catch (NumberFormatException e){
            throw new IncorrectTimeFormatException(
                    "Incorrect Format of time. Hours and minutes should be integers. Example 18:30");
        }
    }

    private TimeInterval validate(String lowerBoundTimeStr, String upperBoundTimeStr) throws IncorrectTimeIntervalException, IncorrectTimeFormatException {

        List<String> times = new ArrayList<>();
        times.add(lowerBoundTimeStr);
        times.add(upperBoundTimeStr);

        for(String timeStr : times){
            this.validateTime(timeStr);
        }

        Time lowerBoundTime = Time.valueOf(lowerBoundTimeStr.trim() + ADDITION_SECONDS);
        Time upperBoundTime = Time.valueOf(upperBoundTimeStr.trim() + ADDITION_SECONDS);

        boolean lowerBoundIsGreaterThanUpperBound =
                lowerBoundTime.getTime() >= upperBoundTime.getTime();
        if (lowerBoundIsGreaterThanUpperBound) {
            throw new IncorrectTimeIntervalException(
                    "Time upper bound should be greater than lower bound");
        }
        return new TimeInterval(lowerBoundTime,upperBoundTime);
    }

    public TimeInterval validate(FindRidesFormParams params) throws IncorrectTimeIntervalException, IncorrectTimeFormatException {
        return this.validate(params.getLowerBoundTime(),params.getUpperBoundTime());
    }
}
