package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.PassengerDao;
import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.datasource.TicketDao;
import com.tsystems.jschool.railage.datasource.TrainRideDao;
import com.tsystems.jschool.railage.domain.*;
import com.tsystems.jschool.railage.service.exceptions.BookingTimeLimitIsOverException;
import com.tsystems.jschool.railage.service.exceptions.InvalidBoardingStationInRouteException;
import com.tsystems.jschool.railage.service.exceptions.NoFreeSeatsForRideException;
import com.tsystems.jschool.railage.service.exceptions.PassengerAlreadyBookedTicketOnRideException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rudolph on 12.07.15.
 */
@Service
@Transactional(readOnly = true)
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TrainRideDao trainRideDao;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private StationDao stationDao;

    public void buyTicket(Integer trainRideId, Integer boardingStationId, Passenger passenger)
            throws NoFreeSeatsForRideException, PassengerAlreadyBookedTicketOnRideException, BookingTimeLimitIsOverException, ParseException, InvalidBoardingStationInRouteException {

        TrainRide ride = trainRideDao.findById(trainRideId);
        long freeSeats = ride.getTrain().getSeats().longValue();
        long bookedTickets = ticketDao.countTicketsByRide(trainRideId);
        if (freeSeats <= bookedTickets) {
            throw new NoFreeSeatsForRideException(
                    "No Free seats for train ride.");
        }

        List<Passenger> passengers =  passengerDao.findBy(trainRideId,passenger);

        if (!passengers.isEmpty()) {
            throw new PassengerAlreadyBookedTicketOnRideException(
                    "This passenger has already booked ticket on this train ride");
        }

        long currentTime = new Date().getTime();
        long rideTime = this.getRideTimeOnBoardingStation(ride,boardingStationId);
        long delta = 600000; // 10 minutes in ms
        if( rideTime - currentTime < delta ){
            throw new BookingTimeLimitIsOverException(
                    "Booking time limit is over." +
                            " Time before train departure is less than 10 minutes"
            );
        }

        Station boardingStation = stationDao.findById(boardingStationId);

        Ticket ticket = new Ticket(passenger,ride,boardingStation);
        ticketDao.merge(ticket);
    }

    private long getRideTimeOnBoardingStation(TrainRide ride, Integer boardingStationId) throws ParseException, InvalidBoardingStationInRouteException {
        String rideDateStr = ride.getDate().toString();
        TimeInfo timeInfo = ride.getRoute().getTimeInfoByStationId(boardingStationId);
        if (timeInfo == null) {
            throw new InvalidBoardingStationInRouteException(
                            "Invalid boarding in selected station");
        }
        String rideTimeStr = timeInfo.getDepartureTime().toString();
        String rideTimeStampStr = rideDateStr.trim() + " " + rideTimeStr.trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date rideDate = dateFormat.parse(rideTimeStampStr);
        return rideDate.getTime();
    }



}
