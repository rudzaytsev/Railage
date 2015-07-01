package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.PassengerDao;
import com.tsystems.jschool.railage.domain.Passenger;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class PassengerService {

    private static PassengerDao passengerDao = new PassengerDao();

    public static List<Passenger> findPassengersByRideId(Integer id){
       return passengerDao.findByRideId(id);
    }

    public static List<Passenger> findPassengersByTrainId(Integer id){
        return passengerDao.findByTrainId(id);
    }
}
