package com.tsystems.jschool.railage.domain;

import javax.persistence.*;

/**
 * Represents time table line for some railway
 * station in domain of information system
 * Time table line contains train, station, route and time data.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "TimeTableLines")
public class TimeTableLine extends DomainObject {

    /** train */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinColumn(name = "trainId")
    private Train train;

    /** railway station */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinColumn(name = "stationId")
    private Station station;

    /** information about time when train visits railway station */
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinColumn(name = "timeInfoId")
    private TimeInfo timeInfo;

    /* route of train */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinColumn(name = "routeId")
    private Route route;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TimeInfo getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


}
