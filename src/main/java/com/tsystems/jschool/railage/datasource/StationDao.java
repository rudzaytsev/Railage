package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Station;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationDao extends JpaDao<Station> {

    @Override
    public void update(Station entity) {

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
}
