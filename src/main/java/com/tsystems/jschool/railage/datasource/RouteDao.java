package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Route;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 02.07.15.
 */
public class RouteDao extends JpaDao<Route> {

    @Override
    public void update(Route entity) {

    }

    @Override
    public Route findById(Integer id) {

        String queryStr = "SELECT r FROM Route r WHERE r.id = ?1";
        TypedQuery<Route> query = entityManager.createQuery(
                queryStr, Route.class);
        query.setParameter(1,id);
        Route requiredRoute = null;
        try {
            requiredRoute = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredRoute;
    }

    @Override
    public List<Route> findAll() {

        String queryStr = "SELECT r FROM Route r ORDER BY r.id";
        TypedQuery<Route> query = entityManager.createQuery(
                                                queryStr, Route.class);
        return query.getResultList();
    }
}
