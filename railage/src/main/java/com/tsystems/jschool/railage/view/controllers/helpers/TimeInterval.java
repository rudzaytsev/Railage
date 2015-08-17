package com.tsystems.jschool.railage.view.controllers.helpers;

import java.sql.Time;

/**
 * Created by rudolph on 10.07.15.
 */
public class TimeInterval {

    private Time lowerBound;

    private Time upperBound;

    public TimeInterval(Time lowerBound, Time upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Time getLowerBound() {
        return lowerBound;
    }

    public Time getUpperBound() {
        return upperBound;
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                '}';
    }
}
