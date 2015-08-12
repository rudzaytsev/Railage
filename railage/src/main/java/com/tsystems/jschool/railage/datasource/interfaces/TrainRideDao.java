package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.view.controllers.helpers.TimeInterval;

import java.util.List;

/**
 * Created by rudolph on 28.07.15.
 */
public interface TrainRideDao extends Dao<TrainRide, Integer> {
    @Override
    TrainRide merge(TrainRide entity);

    @Override
    TrainRide findById(Integer id);

    @Override
    List<TrainRide> findAll();

    List<TrainRide> findRidesBy(Integer sourceStationId, Integer destStaionId,
                                TimeInterval interval);
}
