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

    private static TrainDao trainDao = new TrainDao();

    public static List<Train> findAllTrains(){
        return trainDao.findAll();
    }

    public static List<TrainRide> findAllRidesByTrainId(Integer id){
        Train train = trainDao.findById(id);
        return (train != null) ? train.getRides() : null;
    }

    public static Train findTrainById(Integer id){
        return trainDao.findById(id);
    }

    public static void addTrain(String trainNumber,Integer seats)
            throws NotPositiveNumberOfSeatsException, DomainObjectAlreadyExistsException,
            IncorrectParameterException {

        if (seats <= 0){
            throw new NotPositiveNumberOfSeatsException(
                    "Number of seats should be positive");
        }
        if (!trainNumber.matches("^[0-9a-zA-Z]+")){
            throw new IncorrectParameterException(
                    "Train number should contains only latin characters and digits");
        }
        List<Train> trains = trainDao.findTrainsByTrainNumber(trainNumber);
        if (!trains.isEmpty()){
            throw new DomainObjectAlreadyExistsException(
                      "Train with such parameters already exists");
        }
        trainDao.persist(new Train(seats,trainNumber));

    }


    public static Train merge(Train train){
        return trainDao.merge(train);
    }
}
