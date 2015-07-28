package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
@Service
@Transactional(readOnly = true)
public class StationService {

    @Autowired
    private StationDao stationDao;

    private static final String STATION_NAME_PATTERN = "^[0-9a-zA-Z]+";

    public List<Station> findAllStations(){
        return stationDao.findAll();
    }

    public Station findStationById(Integer id){
        return stationDao.findById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void addStation(String stationName)
            throws DomainObjectAlreadyExistsException, IncorrectParameterException {

       if (!stationName.matches(STATION_NAME_PATTERN)){
            throw new IncorrectParameterException(
                    "Station name should contains only latin characters and digits");
       }
       List<Station> stations = stationDao.findStationsByName(stationName);
       if (!stations.isEmpty()) {
           throw new DomainObjectAlreadyExistsException(
                   "Station with such parameters already exists");
       }
       stationDao.persist(new Station(stationName));
    }
}
