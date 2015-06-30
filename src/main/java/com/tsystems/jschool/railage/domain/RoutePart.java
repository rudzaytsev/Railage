package com.tsystems.jschool.railage.domain;

import javax.persistence.*;

/**
 * Represents part of train route
 */
@Entity
@Table(name = "RouteParts")
public class RoutePart extends DomainObject {


    /** route of route part */
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    /** current train station of route part */
    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    /** next train route part in route */
    @OneToOne
    @JoinColumn(name = "nextRoutePartId")
    private RoutePart next;

    /**
     * current station status in route
     * for example: START, END or STAND
     */
    private String status;

    public RoutePart(){
        //does nothing
    }

    public RoutePart(Station station, String status) {
        this.station = station;
        this.status = status;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public RoutePart getNext() {
        return next;
    }

    public void setNext(RoutePart next) {
        this.next = next;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
