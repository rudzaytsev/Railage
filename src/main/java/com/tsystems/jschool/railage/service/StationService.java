package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationService {

    private static StationDao stationDao = new StationDao();

    public static List<Station> findAllStations(){
        return stationDao.findAll();
    }

    public static void addStation(String stationName)
                        throws DomainObjectAlreadyExistsException {

       List<Station> stations = stationDao.findStationsByName(stationName);
       if (!stations.isEmpty()){
             throw new DomainObjectAlreadyExistsException(
                     "Station with such parameters already exists");
       }
       else {
           stationDao.persist(new Station(stationName));
       }
    }
}
