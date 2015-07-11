package com.tsystems.jschool.railage.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Represents train route in information system's
 * domain. Route relates to train and consists of
 * 2 or more route parts
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Routes")
public class Route extends DomainObject {

    /** train that corresponds to route */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "trainId")
    private Train train;

    /** list of parts of the route */
    @OneToMany(mappedBy = "route",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<RoutePart> routeParts;

    @OneToMany(mappedBy = "route",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeTableLine> timeTableLines;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<RoutePart> getRouteParts() {
        return routeParts;
    }

    public void setRouteParts(List<RoutePart> routeParts) {
        this.routeParts = routeParts;
    }

    public RoutePart getStartRoutePart(){
        return getRoutePartWithStatus(RoutePartStatuses.START);
    }

    public RoutePart getEndRoutePart(){
        return getRoutePartWithStatus(RoutePartStatuses.END);
    }


    public TimeInfo getTimeInfoByStationId(Integer stationId){

        for( TimeTableLine timeTable : timeTableLines ){
           Station station = timeTable.getStation();
           if(stationId.equals(station.getId())) {
               return timeTable.getTimeInfo();
           }
        }
        return null;
    }


    private RoutePart getRoutePartWithStatus(RoutePartStatuses status){
        String requiredStatus = status.value();
        for (RoutePart routePart : routeParts){
            String currentStatus = routePart.getStatus();
            if (requiredStatus.equals(currentStatus)){
                return routePart;
            }
        }
        return null;
    }

    public Station getStartStation(){
        RoutePart startRoutePart = getStartRoutePart();
        if (startRoutePart != null){
            return startRoutePart.getStation();
        }
        return null;
    }

    public Station getEndStation(){
        RoutePart endRoutePart = getEndRoutePart();
        if (endRoutePart != null){
            return endRoutePart.getStation();
        }
        return null;
    }

    public String getRouteName(){
        StringBuilder builder = new StringBuilder();
        String delim = "--";
        builder.append(this.getId());
        builder.append(delim);
        builder.append(this.getStartStation().getName());
        builder.append(delim);
        builder.append(this.getEndStation().getName());
        return builder.toString();
    }

    public String getPeriod(){
        return timeTableLines.get(0).getTimeInfo().getPeriod();
    }


    public List<TimeTableLine> getTimeTableLines() {
        return timeTableLines;
    }

    public void setTimeTableLines(List<TimeTableLine> timeTableLines) {
        this.timeTableLines = timeTableLines;
    }
}
