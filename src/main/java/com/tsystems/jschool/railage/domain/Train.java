package com.tsystems.jschool.railage.domain;

import java.util.List;

/**
 * Represents train as vehicle in information
 * system's domain.
 * Train should contains number of seats,
 * list of train rides and list of routes.
 * @author Rudolph Zaytsev
 */
public class Train extends DomainObject {

    /** max number of seats in the train */
    private Integer seats;

    /** list of train rides, done by train */
    private List<TrainRide> rides;

    /** list of train routes */
    private List<Route> routes;

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public List<TrainRide> getRides() {
        return rides;
    }

    public void setRides(List<TrainRide> rides) {
        this.rides = rides;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
