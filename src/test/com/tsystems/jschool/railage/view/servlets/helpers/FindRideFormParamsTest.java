package com.tsystems.jschool.railage.view.servlets.helpers;

import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeIntervalException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindRideFormParamsTest {

    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {

        request = mock(HttpServletRequest.class);

    }

    @Test
    public void testFill01() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("12:00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeIntervalException.class)
    public void testFill02() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("10:00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeIntervalException.class)
    public void testFill03() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("9:00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill04() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("25:00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill05() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("-10:00");
        when(request.getParameter("upperBoundTime")).thenReturn("20:00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill06() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10-00");
        when(request.getParameter("upperBoundTime")).thenReturn("20-00");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }

    @Test(expected = IncorrectTimeFormatException.class)
    public void testFill07() throws Exception {

        when(request.getParameter("fromStationId")).thenReturn("1");
        when(request.getParameter("toStationId")).thenReturn("2");
        when(request.getParameter("lowerBoundTime")).thenReturn("10-00");
        when(request.getParameter("upperBoundTime")).thenReturn("20-61");

        FindRideFormParams params = new FindRideFormParams();
        params.fill(request);

    }
}