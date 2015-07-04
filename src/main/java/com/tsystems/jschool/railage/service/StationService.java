package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.domain.Station;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationService {

    private static StationDao stationDao = new StationDao();

    public static List<Station> findAllStations(){
        return stationDao.findAll();
    }

    public static boolean addStation(String stationName){
       List<Station> stations = stationDao.findStationsByName(stationName);
       if (stations.isEmpty()){
           stationDao.persist(new Station(stationName));
           return true;
       }
       return false;
    }
}
