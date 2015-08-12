package com.tsystems.jschool.railage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

/**
 * Represents time information about train
 * visits of railway station
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "TimeInfos")
public class TimeInfo extends DomainObject {

    /**
     * Flag of periodic time information.
     * This flag means that train visits have a period,
     * for example: every day or on weekends.
     * If this flag is true, fixedDateTime member is false
     */
    @Column(name = "periodic")
    private boolean periodicInfo;

    /** period of train station visiting */
    private String period;

    /** train arrival time */
    private Time arrivalTime;

    /** train departure time */
    private Time departureTime;

    /** fixed date of train visit */
    private Date fixedDate;

    public boolean isPeriodicInfo() {
        return periodicInfo;
    }

    public void setPeriodicInfo(boolean periodicInfo) {
        this.periodicInfo = periodicInfo;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }
}
