package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Passenger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PassengerDaoImpl extends JpaDao<Passenger> implements PassengerDao {


    /*
    @Override
    public Passenger merge(Passenger entity) {

        EntityTransaction transaction = entityManager.getTransaction();
        Passenger result = null;
        try {
            transaction.begin();
            result = entityManager.merge(entity);
            transaction.commit();
        }
        finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
        return result;
    }
    */

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

    @Override
    public List<Passenger> findByRideId(Integer id){

        String queryStr = "SELECT t.passenger FROM Ticket t WHERE t.trainRide.id = ?1";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                            queryStr, Passenger.class);
        query.setParameter(1,id);
        return query.getResultList();
    }

    @Override
    public List<Passenger> findByTrainId(Integer id){

        String queryStr = "SELECT DISTINCT t.passenger FROM Ticket t WHERE t.trainRide.train.id = ?1";
        TypedQuery<Passenger> query = entityManager.createQuery(
                                                queryStr, Passenger.class);
        query.setParameter(1,id);
        return query.getResultList();
    }

    @Override
    public List<Passenger> findBy(Integer rideId, Passenger passenger){

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT DISTINCT t.passenger FROM Ticket t ")
               .append("WHERE t.passenger.name = ?1 ")
               .append("AND t.passenger.lastName = ?2 ")
               .append("AND t.passenger.birthDate = ?3 ")
               .append("AND t.trainRide.id = ?4 ");

        String queryStr = builder.toString();
        TypedQuery<Passenger> query = entityManager.createQuery(
                queryStr, Passenger.class);
        query.setParameter(1,passenger.getName());
        query.setParameter(2,passenger.getLastName());
        query.setParameter(3,passenger.getBirthDate());
        query.setParameter(4,rideId);

        return query.getResultList();
    }
}
