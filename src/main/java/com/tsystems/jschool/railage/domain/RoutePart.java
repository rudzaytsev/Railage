package com.tsystems.jschool.railage.domain;

/**
 * Represents part of train route
 */
public class RoutePart extends DomainObject {

    /** route of route part */
    private Route route;

    /** current train station of route part */
    private Station station;

    /** next train route part in route */
    private RoutePart next;

    /**
     * current station status in route
     * for example: START, FINISH or STAND
     */
    private String status;

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
