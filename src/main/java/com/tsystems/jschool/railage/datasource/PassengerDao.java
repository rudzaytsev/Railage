package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Passenger;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class PassengerDao extends JpaDao<Passenger> {


    @Override
    public void update(Passenger entity) {

    }

    @Override
    public Passenger findById(Integer id) {

        String queryStr = "SELECT p FROM Passenger p WHERE p.id = ?1";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                            queryStr, Passenger.class);
        query.setParameter(1,id);
        Passenger requiredPassenger = null;
        try {
            requiredPassenger = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredPassenger;
    }

    @Override
    public List<Passenger> findAll() {

        String queryStr = "SELECT p FROM Passenger p";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                            queryStr, Passenger.class);
        return query.getResultList();
    }

    public List<Passenger> findByRideId(Integer id){

        String queryStr = "SELECT t.passenger FROM Ticket t WHERE t.trainRide.id = ?1";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                            queryStr, Passenger.class);
        query.setParameter(1,id);
        return query.getResultList();
    }

    public List<Passenger> findByTrainId(Integer id){

        String queryStr = "SELECT DISTINCT t.passenger FROM Ticket t WHERE t.trainRide.train.id = ?1";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                                queryStr, Passenger.class);
        query.setParameter(1,id);
        return query.getResultList();
    }
}
