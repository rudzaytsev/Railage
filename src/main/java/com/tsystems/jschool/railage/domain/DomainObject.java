package com.tsystems.jschool.railage.domain;

/**
 * Represents domain object - the root of all objects
 * in information system domain
 * @author Rudolph Zaytsev
 */
public abstract class DomainObject {


    /** Unique id of DomainObject instance */
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
