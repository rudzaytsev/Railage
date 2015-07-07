package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.PassengerDao;
import com.tsystems.jschool.railage.domain.Passenger;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class PassengerService {

    private PassengerDao passengerDao = new PassengerDao();

    public List<Passenger> findPassengersByRideId(Integer id){
        List<Passenger> passengers;
        passengerDao.open();
        try {
            passengers = passengerDao.findByRideId(id);
        }
        finally {
            passengerDao.close();
        }
       return passengers;
    }

    public List<Passenger> findPassengersByTrainId(Integer id){
        List<Passenger> passengers;
        passengerDao.open();
        try {
            passengers = passengerDao.findByTrainId(id);
        }
        finally {
            passengerDao.close();
        }
        return passengers;
    }
}
