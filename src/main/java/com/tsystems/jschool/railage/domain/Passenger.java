package com.tsystems.jschool.railage.domain;

import java.util.Date;

/**
 * Represents passenger of train in domain
 * of information system
 * @author Rudolph Zaytsev
 */
public class Passenger extends DomainObject {

    /** Passenger's name */
    private String name;

    /** Passenger's lastname */
    private String lastName;

    /** Passenger's date of birth */
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
