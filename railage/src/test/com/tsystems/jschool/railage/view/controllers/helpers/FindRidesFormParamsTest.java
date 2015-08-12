package com.tsystems.jschool.railage.view.controllers.helpers;

import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeIntervalException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindRidesFormParamsTest {

    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {

        request = mock(HttpServletRequest.class);

    }


    @Ignore("need change")
    @Test
    public void testFill01() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("12:00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeIntervalException.class)
    public void testFill02() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("10:00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeIntervalException.class)
    public void testFill03() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("9:00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill04() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("25:00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill05() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("-10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("20:00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill06() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10-00");
        when(request.getParameter("upperBoundTime")).thenReturn("20-00");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }

    @Ignore("need change")
    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill07() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10-00");
        when(request.getParameter("upperBoundTime")).thenReturn("20-61");

        FindRidesFormParams params = new FindRidesFormParams();
        //params.fill(request);

    }
}