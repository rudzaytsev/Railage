package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.impls.RouteDaoImpl;
import com.tsystems.jschool.railage.datasource.impls.TrainDaoImpl;
import com.tsystems.jschool.railage.datasource.impls.TrainRideDaoImpl;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.Train;
import com.tsystems.jschool.railage.domain.enums.Period;
import com.tsystems.jschool.railage.service.exceptions.*;
import com.tsystems.jschool.railage.view.controllers.helpers.FindRidesFormParams;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainServiceTest {


    TrainService service;


    @Before
    public void setUp() throws Exception {

        service = new TrainService();
        service.trainDao = mock(TrainDaoImpl.class);
        service.trainRideDao = mock(TrainRideDaoImpl.class);
        service.routeDao = mock(RouteDaoImpl.class);

    }

    @Test(expected = IncorrectParameterException.class)
    public void testAddTrainInvalidTrainNumber() throws Exception {
        service.addTrain("*&^AAA90KL",50);
    }

    @Test(expected = IncorrectParameterException.class)
    public void testAddTrainInvalidTrainNumberWithNotLatinChar() throws Exception {
        service.addTrain("AAA90KLЫ",50);
    }

    @Test(expected = NotPositiveNumberOfSeatsException.class)
    public void testAddTrainInvalidNegativeNumberOfSeats() throws Exception {
        service.addTrain("MKJ8899",-50);
    }

    @Test(expected = NotPositiveNumberOfSeatsException.class)
    public void testAddTrainInvalidZeroNumberOfSeats() throws Exception {
        service.addTrain("MKJ88977",0);
    }

    @Test(expected = DomainObjectAlreadyExistsException.class)
    public void testAddTrainDomainObjectAlreadyExists() throws Exception {
        String trainNumber = "MKJ88977";
        List<Train> trains = new LinkedList<Train>();
        trains.add(new Train(10,"trainNumber"));
        trains.add(new Train(10,"trainNumber"));
        when(service.trainDao.findTrainsByTrainNumber(trainNumber)).
                thenReturn(trains);
        service.addTrain(trainNumber,10);
    }

    @Test(expected = java.text.ParseException.class)
    public void testAddTrainRideOnWrongDateFormat() throws Exception {
        Route route = mock(Route.class);
        route.setTrain(new Train());
        when(route.getPeriod()).thenReturn(Period.WEEKENDS.toString());

        when(service.routeDao.findById(anyInt())).thenReturn(route);
        service.addTrainRide(55,"15-08-2015","500");
    }

    @Test(expected = InvalidPriceException.class)
    public void testAddTrainRideOnNegativePrice() throws Exception {
        Route route = mock(Route.class);
        route.setTrain(new Train());
        when(route.getPeriod()).thenReturn(Period.WEEKENDS.toString());

        when(service.routeDao.findById(anyInt())).thenReturn(route);
        service.addTrainRide(55,"2015-08-15","-500");
    }

    @Test(expected = InvalidPriceException.class)
    public void testAddTrainRideOnZeroPrice() throws Exception {
        Route route = mock(Route.class);
        route.setTrain(new Train());
        when(route.getPeriod()).thenReturn(Period.WEEKENDS.toString());

        when(service.routeDao.findById(anyInt())).thenReturn(route);
        service.addTrainRide(55,"2015-08-15","0");
    }

    @Test(expected = TimeTableConflictException.class)
    public void testAddTrainRideDateMustBeWeekEnd() throws Exception {
        Route route = mock(Route.class);
        route.setTrain(new Train());
        when(route.getPeriod()).thenReturn(Period.WEEKENDS.toString());

        when(service.routeDao.findById(anyInt())).thenReturn(route);
        service.addTrainRide(55,"2015-08-17","1000");
    }

    @Test(expected = java.text.ParseException.class)
    public void testAddTrainRideDateNotExist() throws Exception {
        Route route = mock(Route.class);
        route.setTrain(new Train());
        when(route.getPeriod()).thenReturn(Period.EVERY_DAY.toString());

        when(service.routeDao.findById(anyInt())).thenReturn(route);
        service.addTrainRide(55,"2015-02-31","1000");
    }


    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateIncorrectTimeFormat() throws Exception {
        FindRidesFormParams params = new FindRidesFormParams();
        params.setLowerBoundTime("10-00");
        params.setUpperBoundTime("11-00");
        service.validate(params);
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateIncorrectHours() throws Exception {
        FindRidesFormParams params = new FindRidesFormParams();
        params.setLowerBoundTime("10:00");
        params.setUpperBoundTime("25:00");
        service.validate(params);
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateIncorrectMinutes() throws Exception {
        FindRidesFormParams params = new FindRidesFormParams();
        params.setLowerBoundTime("10:00");
        params.setUpperBoundTime("23:71");
        service.validate(params);
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateNotParsepleTime() throws Exception {
        FindRidesFormParams params = new FindRidesFormParams();
        params.setLowerBoundTime("10:$0");
        params.setUpperBoundTime("23:01");
        service.validate(params);
    }

    @Test(expected = IncorrectTimeIntervalException.class)
    public void testValidateLoweBoundIsGreaterThenUpperBound() throws Exception {
        FindRidesFormParams params = new FindRidesFormParams();
        params.setLowerBoundTime("11:00");
        params.setUpperBoundTime("10:00");
        service.validate(params);
    }
}