package com.tsystems.jschool.railage.domain;

/**
 * Represents domain object - the root of all objects
 * in information system domain
 * @author Rudolph Zaytsev
 */
public abstract class DomainObject {


    /** Unique id of DomainObject instance */
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
