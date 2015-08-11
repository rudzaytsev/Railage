package com.tsystems.jschool.railage.datasource.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Data Access Object interface
 * @author Rudolph Zaytsev
 */
public interface Dao<T,K extends Serializable> {

    /**
     * Saves entity to persistence context.
     * @param entity - entity to save in persistence context
     */
    void persist(T entity);

    /**
     * Updates entity in persistence context
     * @param entity to merge in persistence context
     * @return entity that merged to persistence context
     */
    T merge(T entity);

    /**
     * Finds entity with specified id value from persistence context
     * @param id - unique id for searching entity
     * @return entity with specified id value
     */
    T findById(K id);

    /**
     * Finds all entities in persistence context
     * @return list of all entities in persistence context
     */
    List<T> findAll();

}
