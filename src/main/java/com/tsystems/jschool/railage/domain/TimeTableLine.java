package com.tsystems.jschool.railage.domain;

import javax.persistence.*;

/**
 * Represents time table line for some railway
 * station in domain of information system
 * Time table line contains train, station and time data.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "TimeTableLines")
public class TimeTableLine extends DomainObject {

    /** train */
    @ManyToOne
    @JoinColumn(name = "trainId")
    private Train train;

    /** railway station */
    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    /** information about time when train visits railway station */
    @OneToOne
    @JoinColumn(name = "timeInfoId")
    private TimeInfo timeInfo;

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
}
