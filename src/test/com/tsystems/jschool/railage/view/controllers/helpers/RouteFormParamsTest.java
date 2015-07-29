package com.tsystems.jschool.railage.view.controllers.helpers;

import com.tsystems.jschool.railage.service.exceptions.DuplicatedStationsInRouteException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectStationsDepartureTimesOrderException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class RouteFormParamsTest {

    RouteFormParams params;

    @Before
    public void setUp() throws Exception {
        params = new RouteFormParams();
    }

    @Test
    public void testValidateTimes() throws Exception {

        params.setTimes(Arrays.asList("8:07", "09:08", "19:00", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateTimes2() throws Exception {

        params.setTimes(Arrays.asList("8:07", "09:08", "19:00", "24:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateTimes3() throws Exception {

        params.setTimes(Arrays.asList("-8:07", "09:08", "19:00", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateTimes4() throws Exception {

        params.setTimes(Arrays.asList("8:07", "09:08", "19:71", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateTimes5() throws Exception {

        params.setTimes(Arrays.asList("8:07", "", "19:71", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testValidateTimes6() throws Exception {

        params.setTimes(Arrays.asList("8:07", "null", "19:71", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateTimes7() throws Exception {

        params.setTimes(Arrays.asList("8:07", "8:07", "19:00", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateTimes8() throws Exception {

        params.setTimes(Arrays.asList("8:07", "15:30", "14:22", "20:30"));
        params.validateTimes();
    }

    @Test(expected = IncorrectStationsDepartureTimesOrderException.class)
    public void testValidateTimes9() throws Exception {

        params.setTimes(Arrays.asList("8:07", "15:30", "17:22", "20:30","0:20"));
        params.validateTimes();
    }

    @Test(expected = DuplicatedStationsInRouteException.class)
    public void testValidateStations() throws Exception {

        params.setStationsIds(Arrays.asList(1,2,378,9009,678,89,902,89));
        params.validateStations();
    }
}

