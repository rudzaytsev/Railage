package com.tsystems.jschool.railage.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 30.06.15.
 */
public enum Period {

    EVERY_DAY("EVERYDAY"),WEEKDAYS("WEEKDAYS"),
    WEEKENDS("WEEKENDS");

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

    public static List<String> getPeriodsAsList(){
        ArrayList<String> result = new ArrayList<String>();
        result.add(Period.EVERY_DAY.period);
        result.add(Period.WEEKDAYS.period);
        result.add(Period.WEEKENDS.period);
        return result;
    }
}
