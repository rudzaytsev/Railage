package com.tsystems.jschool.railage.view.servlets.helpers;

import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.TimeInfo;

/**
 * Created by rudolph on 08.07.15.
 */
public class RouteHelper {


    private String trainNumber;
    private Integer seats;
    private Integer stationsNumber;
    private String period;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getStationsNumber() {
        return stationsNumber;
    }

    public void setStationsNumber(Integer stationsNumber) {
        this.stationsNumber = stationsNumber;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public static RouteHelper map(Route route){
        RouteHelper helper = new RouteHelper();
        helper.setTrainNumber(route.getTrain().getNumber());
        helper.setSeats(route.getTrain().getSeats());
        TimeInfo timeInfo = route.getTimeTableLines().get(0).getTimeInfo();
        helper.setPeriod(timeInfo.getPeriod());
        helper.setStationsNumber(route.getRouteParts().size());
        return helper;
    }
}
