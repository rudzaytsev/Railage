package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Train;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 28.06.15.
 */
public class TrainDao extends JpaDao<Train> {

    @Override
    public void update(Train entity) {

    }

    @Override
    public Train findById(Integer id) {
        String queryStr = "SELECT t FROM Train t WHERE t.id = ?1";
        TypedQuery<Train> query = entityManager.createQuery(queryStr, Train.class);
        query.setParameter(1,id);
        Train requiredTrain = null;
        try {
            requiredTrain = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredTrain;
    }

    @Override
    public List<Train> findAll() {
        String queryStr = "SELECT t FROM Train t";
        TypedQuery<Train> query = entityManager.createQuery(queryStr, Train.class);
        return query.getResultList();
    }
}
