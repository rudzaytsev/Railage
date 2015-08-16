package com.tsystems.jschool.railage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Represents user of information system
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Users")
public class User extends DomainObject {

    /** user login */
    @Column(name = "username")
    private String login;

    /** user password */
    private String password;

    @Transient
    private String plainPassword;

    /** user role */
    @Column(name = "authority")
    private String role;

    /** user money balance in $ **/
    private Integer balance;

    public User(){
        balance = 0;
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

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
