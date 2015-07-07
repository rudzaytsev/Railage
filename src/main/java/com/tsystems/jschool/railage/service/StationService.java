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

    private StationDao stationDao = new StationDao();

    public List<Station> findAllStations(){
        List<Station> stations;
        stationDao.open();
        try {
            stations = stationDao.findAll();
        }
        finally {
            stationDao.close();
        }
        return stations;
    }

    public Station findStationById(Integer id){
        Station station;
        stationDao.open();
        try {
            station = stationDao.findById(id);
        }
        finally {
            stationDao.close();
        }
        return station;
    }

    public void addStation(String stationName)
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
           stationDao.open();
           try {
               stationDao.persist(new Station(stationName));
           }
           finally {
               stationDao.close();
           }
       }
    }
}
