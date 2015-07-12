package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.PassengerDao;
import com.tsystems.jschool.railage.domain.Passenger;
import com.tsystems.jschool.railage.view.Utils;

import java.text.ParseException;
import java.util.Date;
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

    public Passenger createPassenger(String name, String lastName, String birthDateStr) throws ParseException {

        Date birthDate = Utils.convert(birthDateStr);
        passengerDao.open();
        Passenger passenger = new Passenger(name,lastName,birthDate);
        Passenger createdPassenger;
        try {
            createdPassenger = passengerDao.merge(passenger);
        }
        finally {
            passengerDao.close();
        }
        return createdPassenger;
    }

    public List<Passenger> findBy(Integer rideId, Passenger passenger){
        List<Passenger> passengers;
        passengerDao.open();
        try {
            passengers = passengerDao.findBy(rideId,passenger);
        }
        finally {
            passengerDao.close();
        }
        return passengers;
    }
}
