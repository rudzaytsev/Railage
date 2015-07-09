package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.TrainRide;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 09.07.15.
 */
public class TrainRideDao extends JpaDao<TrainRide> {


    @Override
    public TrainRide merge(TrainRide entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        TrainRide result = null;
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

    @Override
    public TrainRide findById(Integer id) {
        String queryStr = "SELECT t FROM TrainRide t WHERE t.id = ?1";
        TypedQuery<TrainRide> query = entityManager.createQuery(
                queryStr, TrainRide.class);
        query.setParameter(1,id);
        TrainRide requiredRide = null;
        try {
            requiredRide = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredRide;
    }

    @Override
    public List<TrainRide> findAll() {

        String queryStr = "SELECT t FROM TrainRide t";
        TypedQuery<TrainRide> query = entityManager.createQuery(
                queryStr, TrainRide.class);
        return query.getResultList();
    }
}
