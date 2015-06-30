package com.tsystems.jschool.railage.domain;

/**
 * Created by rudolph on 30.06.15.
 */
public enum Period {

    EVERY_DAY("EVERYDAY"),WEEKDAYS("WEEKDAYS"),
    WEEKENDS("WEEKENDS"), THIS_WEEK("THISWEEK");

    private String period;

    private Period(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return period;
    }

    public String value(){
        return period;
    }
}
