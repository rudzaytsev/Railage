package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.TrainDao;
import com.tsystems.jschool.railage.domain.Train;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import com.tsystems.jschool.railage.service.exceptions.NotPositiveNumberOfSeatsException;

import java.util.List;

/**
 * Created by rudolph on 28.06.15.
 */
public class TrainService {

    private TrainDao trainDao = new TrainDao();

    public List<Train> findAllTrains(){

        List<Train> trains = null;
        trainDao.open();
        try {
            trains = trainDao.findAll();
        }
        finally {
            trainDao.close();
        }
        return trains;
    }

    public List<TrainRide> findAllRidesByTrainId(Integer id){
        trainDao.open();
        Train train;
        try {
            train = trainDao.findById(id);
        }
        finally {
            trainDao.close();
        }
        return (train != null) ? train.getRides() : null;
    }

    public Train findTrainById(Integer id){
        trainDao.open();
        Train train;
        try {
           train = trainDao.findById(id);
        }
        finally {
            trainDao.close();
        }
        return train;
    }

    public void addTrain(String trainNumber,Integer seats)
            throws NotPositiveNumberOfSeatsException, DomainObjectAlreadyExistsException,
            IncorrectParameterException {

        trainDao.open();
        try {
            if (seats <= 0) {
                throw new NotPositiveNumberOfSeatsException(
                        "Number of seats should be positive");
            }
            if (!trainNumber.matches("^[0-9a-zA-Z]+")) {
                throw new IncorrectParameterException(
                        "Train number should contains only latin characters and digits");
            }
            List<Train> trains = trainDao.findTrainsByTrainNumber(trainNumber);
            if (!trains.isEmpty()) {
                throw new DomainObjectAlreadyExistsException(
                        "Train with such parameters already exists");
            }
            trainDao.persist(new Train(seats, trainNumber));
        }
        finally {
            trainDao.close();
        }
    }


    public Train merge(Train train){

        Train mergedTrain;
        trainDao.open();
        try {
            mergedTrain = trainDao.merge(train);
        }
        finally {
            trainDao.close();
        }
        return mergedTrain;
    }
}
