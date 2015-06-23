package com.tsystems.jschool.railage.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by rudolph on 23.06.15.
 */
public abstract class JpaDAO implements DAO {

    protected EntityManager entityManager;

    public JpaDAO(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("cc");
       entityManager = factory.createEntityManager();
    }



}
