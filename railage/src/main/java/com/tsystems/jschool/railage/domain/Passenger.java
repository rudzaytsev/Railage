package com.tsystems.jschool.railage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents passenger of train in domain
 * of information system
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Passengers")
public class Passenger extends DomainObject {

    /** Passenger's name */
    private String name;

    /** Passenger's lastname */
    @Column(name = "lastname")
    private String lastName;

    /** Passenger's date of birth */
    private Date birthDate;

    public Passenger(){
        //does nothing
    }

    public Passenger(String name, String lastName, Date birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

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

    public String getFormattedBirthDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(birthDate);
    }
}
