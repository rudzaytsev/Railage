package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.TrainDao;
import com.tsystems.jschool.railage.domain.Train;
import com.tsystems.jschool.railage.domain.TrainRide;

import java.util.List;

/**
 * Created by rudolph on 28.06.15.
 */
public class TrainService {

    private static TrainDao trainDao = new TrainDao();

    public static List<Train> findAllTrains(){
        return trainDao.findAll();
    }

    public static List<TrainRide> findAllRidesByTrainId(Integer id){
        Train train = trainDao.findById(id);
        return (train != null) ? train.getRides() : null;
    }
}
