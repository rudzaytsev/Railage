package com.tsystems.jschool.railage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Represents train as vehicle in information
 * system's domain.
 * Train should contains number of seats, train number,
 * list of train rides and list of routes.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Trains")
public class Train extends DomainObject {

    /** max number of seats in the train */
    @Column(name = "maxSeats")
    private Integer seats;

    /** list of train rides, done by train */
    @OneToMany(mappedBy = "train")
    private List<TrainRide> rides;

    /** list of train routes */
    @OneToMany(mappedBy = "train")
    private List<Route> routes;

    /** train number as String */
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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
