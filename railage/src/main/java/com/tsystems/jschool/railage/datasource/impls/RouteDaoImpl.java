package com.tsystems.jschool.railage.datasource.impls;

import com.tsystems.jschool.railage.datasource.interfaces.RouteDao;
import com.tsystems.jschool.railage.domain.Route;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 02.07.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class RouteDaoImpl extends JpaDao<Route> implements RouteDao {


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
