package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Passenger;

import java.util.List;

/**
 * Created by rudolph on 31.07.15.
 */
public interface PassengerDao extends Dao<Passenger, Integer> {
    @Override
    Passenger merge(Passenger entity);

    @Override
    Passenger findById(Integer id);

    @Override
    List<Passenger> findAll();

    List<Passenger> findByRideId(Integer id);

    List<Passenger> findByTrainId(Integer id);

    List<Passenger> findBy(Integer rideId, Passenger passenger);
}
