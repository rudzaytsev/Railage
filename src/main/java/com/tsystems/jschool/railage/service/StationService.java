package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationService {

    private static StationDao stationDao = new StationDao();

    public static List<Station> findAllStations(){
        return stationDao.findAll();
    }

    public static Station findStationById(Integer id){
        return stationDao.findById(id);
    }

    public static void addStation(String stationName)
            throws DomainObjectAlreadyExistsException, IncorrectParameterException {

        if (!stationName.matches("^[0-9a-zA-Z]+")){
            throw new IncorrectParameterException(
                    "Station name should contains only latin characters and digits");
        }

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
