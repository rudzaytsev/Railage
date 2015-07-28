package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.TrainDao;
import com.tsystems.jschool.railage.datasource.TrainRideDao;
import com.tsystems.jschool.railage.domain.Period;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.Train;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import com.tsystems.jschool.railage.service.exceptions.NotPositiveNumberOfSeatsException;
import com.tsystems.jschool.railage.service.exceptions.TimeTableConflictException;
import com.tsystems.jschool.railage.view.servlets.helpers.TimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private static final String TRAIN_NUMBER_PATTERN = "^[0-9a-zA-Z]+";


    public List<Train> findAllTrains(){
        return trainDao.findAll();
    }


    public List<TrainRide> findAllRidesByTrainId(Integer id){
        //trainDao.open();
        Train train;
        List<TrainRide> rides = null;
        try {
            train = trainDao.findById(id);
            if(train != null) {
                rides = train.getRides();
            }
        }
        finally {
            //trainDao.close();
        }
        return rides;
    }

    public Train findTrainById(Integer id){
        //trainDao.open();
        Train train;
        try {
           train = trainDao.findById(id);
        }
        finally {
           // trainDao.close();
        }
        return train;
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

    public void addTrainRide(Integer routeId, String dateStr) throws ParseException, TimeTableConflictException {

        RouteService routeService = new RouteService();
        Route route = routeService.findRouteById(routeId);
        Train train = route.getTrain();
        java.sql.Date date = this.validateDate(dateStr,route.getPeriod());

        //trainRideDao.open();
        try {

            TrainRide ride = new TrainRide(route,date,train);
            trainRideDao.merge(ride);

        }
        finally {
            //trainRideDao.close();
        }
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

        Train mergedTrain;
        //trainDao.open();
        try {
            mergedTrain = trainDao.merge(train);
        }
        finally {
            //trainDao.close();
        }
        return mergedTrain;
    }

    public List<TrainRide> findAllTrainRides(){
        List<TrainRide> result;
        //trainRideDao.open();
        try {
            result = trainRideDao.findAll();
        }
        finally {
            //trainRideDao.close();
        }
        return result;
    }


    public TrainRide findTrainRideById(Integer id){
        TrainRide ride;
        //trainRideDao.open();
        try {
            ride = trainRideDao.findById(id);
        }
        finally {
            //trainRideDao.close();
        }
        return ride;
    }

    public List<TrainRide> findRidesBy(Integer sourceStationId, Integer destStaionId,
                                       TimeInterval interval) {

        //trainRideDao.open();
        List<TrainRide> result;
        try {
            result = trainRideDao.findRidesBy(
                            sourceStationId,destStaionId,interval);
        }
        finally {
            //trainRideDao.close();
        }
        return result;
    }
}
