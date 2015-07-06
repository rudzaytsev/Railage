package com.tsystems.jschool.railage.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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


    /**
     * current station status in route
     * for example: START, END or STAND
     */
    private String status;

    private Integer position;

    public RoutePart(){
        //does nothing
    }

    public RoutePart(Station station, String status) {
        this.station = station;
        this.status = status;
    }

    public RoutePart(Station station, String status, Integer position) {
        this.station = station;
        this.status = status;
        this.position = position;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


}
