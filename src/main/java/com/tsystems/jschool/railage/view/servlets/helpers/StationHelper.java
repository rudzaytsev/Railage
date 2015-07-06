package com.tsystems.jschool.railage.view.servlets.helpers;

import com.tsystems.jschool.railage.domain.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 06.07.15.
 */
public class StationHelper {

    private Integer stationId;

    private String stationName;

    public StationHelper(Integer stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public static List<StationHelper> map(List<Station> stations){

        ArrayList<StationHelper> result = new ArrayList<>();
        for(Station station : stations){
            result.add(new StationHelper(station.getId(),station.getName()));
        }
        return result;
    }
}
