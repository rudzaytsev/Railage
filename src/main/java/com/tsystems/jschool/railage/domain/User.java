package com.tsystems.jschool.railage.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Represents user of information system
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Users")
public class User extends DomainObject {

    /** user login */
    private String login;

    /** user password */
    private String password;

    /** user role */
    private String role;

    public User(){
        // does nothing
    }

    public User(String login, String password, String role) {
        this(login,password);
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
