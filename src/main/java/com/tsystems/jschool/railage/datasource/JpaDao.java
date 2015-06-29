package com.tsystems.jschool.railage.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rudolph on 23.06.15.
 */
public abstract class JpaDao<T> implements Dao<T, Integer>  {

    protected EntityManager entityManager;

    public JpaDao(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("RailagePU");
       entityManager = factory.createEntityManager();
    }

    @Override
    public void persist(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        }
        finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

}
