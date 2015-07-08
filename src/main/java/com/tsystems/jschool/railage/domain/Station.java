package com.tsystems.jschool.railage.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Represents railway station in information system's domain.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Stations")
public class Station extends DomainObject {

    /** name of railway station. */
    private String name;

    /** timetable of trains, which ride across the station. */
    @OneToMany(mappedBy = "station",fetch = FetchType.EAGER,
               cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeTableLine> timeTableLines;

    public Station(){
        //does nothing
    }


    public Station(String name) {
        this.name = name;
    }

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

    public void addTimeTableLine(TimeTableLine timeTableLine){
        this.timeTableLines.add(timeTableLine);
    }
}
