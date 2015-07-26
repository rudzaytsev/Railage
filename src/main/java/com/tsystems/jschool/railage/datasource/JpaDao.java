package com.tsystems.jschool.railage.datasource;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * Created by rudolph on 23.06.15.
 */
@Repository
public abstract class JpaDao<T> implements Dao<T, Integer>  {

    //protected EntityManagerFactory factory;

    @PersistenceContext
    protected EntityManager entityManager;

    private static final String PersistanceUnitName = "RailagePU";

    public JpaDao(){
       // does nothing
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

    public final void open(){

        /*

        boolean needOpening = (entityManager == null) || !entityManager.isOpen();
        if (needOpening) {
            factory = Persistence.createEntityManagerFactory(PersistanceUnitName);
            entityManager = factory.createEntityManager();
        }
        */
    }

    public final void close(){

        /*
        if(entityManager.isOpen()){
            entityManager.close();
        }
        if(factory.isOpen()){
            factory.close();
        }
        */
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
