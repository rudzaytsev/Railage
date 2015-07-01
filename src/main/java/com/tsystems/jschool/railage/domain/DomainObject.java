package com.tsystems.jschool.railage.domain;

import javax.persistence.*;

/**
 * Represents domain object - the root of all objects
 * in information system domain
 * @author Rudolph Zaytsev
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DomainObject {


    /** Unique id of DomainObject instance */
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainObject)) return false;

        DomainObject that = (DomainObject) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
