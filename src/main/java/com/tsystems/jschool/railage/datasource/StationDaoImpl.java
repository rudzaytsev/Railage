package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Station;
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
public class StationDaoImpl extends JpaDao<Station> implements StationDao {

    @Override
    public Station merge(Station entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Station findById(Integer id) {

        String queryStr = "SELECT s FROM Station s WHERE s.id = ?1";
        TypedQuery<Station> query = entityManager.createQuery(
                                                queryStr, Station.class);
        query.setParameter(1,id);
        Station requiredStation = null;
        try {
            requiredStation = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredStation;
    }

    @Override
    public List<Station> findAll() {

        String queryStr = "SELECT s FROM Station s";
        TypedQuery<Station> query = entityManager.createQuery(
                                                queryStr, Station.class);
        return query.getResultList();
    }


    @Override
    public List<Station> findStationsByName(String stationName){
        String queryStr = "SELECT s FROM Station s WHERE s.name = ?1";
        TypedQuery<Station> query = entityManager.createQuery(
                queryStr, Station.class);
        query.setParameter(1,stationName);
        return query.getResultList();
    }
}
