package com.tsystems.jschool.railage.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by rudolph on 23.06.15.
 */
public abstract class JpaDao {

    protected EntityManager entityManager;

    public JpaDao(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("RailagePU");
       entityManager = factory.createEntityManager();
    }



}
