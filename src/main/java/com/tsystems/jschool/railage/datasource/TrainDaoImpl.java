package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Train;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 28.06.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TrainDaoImpl extends JpaDao<Train> implements TrainDao {

    @Override
    public Train merge(Train entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        Train result = null;
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


    @Override
    public List<Train> findTrainsByTrainNumber(String trainNumber){
        String queryStr = "SELECT t FROM Train t WHERE t.number = ?1";
        TypedQuery<Train> query = entityManager.createQuery(queryStr, Train.class);
        query.setParameter(1,trainNumber);
        return query.getResultList();

    }
}
