package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.PassengerDao;
import com.tsystems.jschool.railage.domain.Passenger;
import com.tsystems.jschool.railage.view.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
@Service
@Transactional(readOnly = true)
public class PassengerService {

    @Autowired
    private PassengerDao passengerDao;

    public List<Passenger> findPassengersByRideId(Integer id){
        return passengerDao.findByRideId(id);
    }

    public List<Passenger> findPassengersByTrainId(Integer id){
        return passengerDao.findByTrainId(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Passenger createPassenger(String name, String lastName, String birthDateStr)
            throws ParseException {

        Date birthDate = Utils.convert(birthDateStr);
        Passenger passenger = new Passenger(name,lastName,birthDate);
        return passengerDao.merge(passenger);
    }

    public List<Passenger> findBy(Integer rideId, Passenger passenger){
        return passengerDao.findBy(rideId,passenger);
    }
}
