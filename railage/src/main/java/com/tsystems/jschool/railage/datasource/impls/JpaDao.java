package com.tsystems.jschool.railage.datasource.impls;

import com.tsystems.jschool.railage.datasource.interfaces.Dao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by rudolph on 23.06.15.
 */
@Repository
public abstract class JpaDao<T> implements Dao<T, Integer> {


    @PersistenceContext
    protected EntityManager entityManager;

    private static final String PersistanceUnitName = "RailagePU";

    public JpaDao(){
       // does nothing
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    public final void open(){
       // does nothing
    }

    public final void close(){
        // does nothing
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}