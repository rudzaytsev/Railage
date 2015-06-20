package com.tsystems.jschool.railage.domain;

import java.util.List;

/**
 * Represents railway station in information system's domain.
 * @author Rudolph Zaytsev
 */
public class Station extends DomainObject {

    /** name of railway station. */
    private String name;

    /** timetable of trains, which ride across the station. */
    private List<TimeTableLine> timeTableLines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeTableLine> getTimeTableLines() {
        return timeTableLines;
    }

    public void setTimeTableLines(List<TimeTableLine> timeTableLines) {
        this.timeTableLines = timeTableLines;
    }
}
