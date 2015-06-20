package com.tsystems.jschool.railage.domain;

/**
 * Represents user of information system
 * @author Rudolph Zaytsev
 */
public class User extends DomainObject {

    /** user login */
    private String login;

    /** user password */
    private String password;

    /** user role */
    private String role;

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
