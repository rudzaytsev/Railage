package com.tsystems.jschool.railage.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RouteTest {

    private  Route route;
    private  RoutePart[] routeParts;

    @Before
    public void setUp() throws Exception {

        route = new Route();
        route.setId(777);
        routeParts = new RoutePart[4];

        routeParts[0] = new RoutePart(
                new Station("A"),
                RoutePartStatuses.START.value());

        routeParts[1] = new RoutePart(
                new Station("B"),
                RoutePartStatuses.STAND.value());

        routeParts[2] = new RoutePart(
                new Station("C"),
                RoutePartStatuses.STAND.value());

        routeParts[3] = new RoutePart(
                new Station("D"),
                RoutePartStatuses.END.value());

        route.setRouteParts(Arrays.asList(routeParts));

    }

    @Test
    public void testGetStartRoutePart() throws Exception {

        RoutePart start = route.getStartRoutePart();

        assertEquals(RoutePartStatuses.START.value(),start.getStatus());
        assertEquals("A",start.getStation().getName());

        route.setRouteParts(
               Arrays.asList(routeParts).subList(1,routeParts.length));

        RoutePart startRoutePart = route.getStartRoutePart();
        assertNull(startRoutePart);
    }

    @Test
    public void testGetEndRoutePart() throws Exception {

        RoutePart end = route.getEndRoutePart();

        assertEquals(RoutePartStatuses.END.value(), end.getStatus());
        assertEquals("D", end.getStation().getName());

        route.setRouteParts(
                Arrays.asList(routeParts).subList(0,routeParts.length - 1));

        RoutePart endRoutePart = route.getEndRoutePart();
        assertNull(endRoutePart);

    }

    @Test
    public void testGetRouteName() throws Exception {
        String result = route.getRouteName();
        assertEquals("777--A--D",result);

        route.setId(888);

        result = route.getRouteName();
        assertEquals("888--A--D",result);
    }
}