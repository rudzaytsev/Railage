package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.service.exceptions.DuplicatedStationsInRouteException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectStationsDepartureTimesOrderException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.view.controllers.helpers.RouteFormParams;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class RouteServiceTest {

    RouteService service;

    RouteFormParams params;

    @Before
    public void setUp() throws Exception {
        params = new RouteFormParams();
        service = new RouteService();
    }

    @Test
    public void testValidate() throws Exception {
        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "09:08", "19:00", "20:30"));
        service.validate(params);
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateIncorrectTimeFormat() throws Exception {
        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "09:08", "19:00", "24:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateNegativeTimeFormat() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("-8:07", "09:08", "19:00", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectTimeFormatException.class)

    public void testValidateNotIncreasedTime() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "09:08", "19:71", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateNotSetTime() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "", "19:71", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateNullAsTime() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "null", "19:71", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateEqualTimes() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "8:07", "19:00", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateWrongSequence() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "15:30", "14:22", "20:30"));
        service.validate(params);
    }


    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateNextDayTime() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,3));
        params.setTimes(Arrays.asList("8:07", "15:30", "17:22", "20:30","0:20"));
        service.validate(params);
    }


    @Test(expected = DuplicatedStationsInRouteException.class)
    public void testValidateStations() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,378,9009,678,89,902,89));
        service.validate(params);
    }

}