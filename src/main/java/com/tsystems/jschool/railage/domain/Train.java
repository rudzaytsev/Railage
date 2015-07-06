package com.tsystems.jschool.railage.domain;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "train",cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private List<TrainRide> rides;

    /** list of train routes */
    @OneToMany(mappedBy = "train",cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Route> routes;

    /** train number as String */
    private String number;

    public Train(){
        //does nothing
    }

    public Train(Integer seats, String number) {
        this.seats = seats;
        this.number = number;
    }

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

    public void addRoute(Route route){
        routes.add(route);
    }
}
