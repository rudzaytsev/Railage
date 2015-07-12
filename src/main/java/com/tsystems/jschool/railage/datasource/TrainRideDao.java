package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.view.servlets.helpers.TimeInterval;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.Time;
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

    public List<TrainRide> findRidesBy(Integer sourceStationId, Integer destStaionId,
                                       TimeInterval interval){

        Time lower = interval.getLowerBound();
        Time upper = interval.getUpperBound();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT DISTINCT ride FROM TrainRide ride, ")
               .append("IN (ride.route.routeParts) fpart, ")
               .append("IN (ride.route.routeParts) spart, ")
               .append("IN (ride.route.timeTableLines) ftimeTable, ")
               .append("IN (ride.route.timeTableLines) stimeTable ")
               .append("WHERE ")
               .append("fpart.position < spart.position ")
               .append("AND fpart.station.id = ?1 AND spart.station.id = ?2 ")
               .append("AND ( ftimeTable.timeInfo.departureTime BETWEEN ?3 AND ?4 ) ")
               .append("AND ( stimeTable.timeInfo.departureTime BETWEEN ?3 AND ?4 ) ")
               .append("AND ftimeTable.station.id = ?1 AND stimeTable.station.id = ?2 ");


        String queryStr = builder.toString();
        TypedQuery<TrainRide> query = entityManager.createQuery(
                queryStr, TrainRide.class);
        query.setParameter(1,sourceStationId);
        query.setParameter(2,destStaionId);
        query.setParameter(3,interval.getLowerBound());
        query.setParameter(4,interval.getUpperBound());
        List<TrainRide> rides = query.getResultList();

        return rides;
    }

}
