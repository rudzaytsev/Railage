package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Train;

import java.util.List;

/**
 * Created by rudolph on 28.07.15.
 */
public interface TrainDao extends Dao<Train, Integer> {
    @Override
    Train merge(Train entity);

    @Override
    Train findById(Integer id);

    @Override
    List<Train> findAll();

    List<Train> findTrainsByTrainNumber(String trainNumber);
}
