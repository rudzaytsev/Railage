package com.tsystems.jschool.railage.domain;

/**
 * Represents time table line for some railway
 * station in domain of information system
 * Time table line contains train and time data.
 * @author Rudolph Zaytsev
 */
public class TimeTableLine extends DomainObject {

    /** train */
    private Train train;

    /** information about time when train visits railway station */
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
}
