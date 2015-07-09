package com.tsystems.jschool.railage.view.servlets;

/**
 * Created by rudolph on 08.07.15.
 */
public enum AjaxRequestType {

    ROUTE("route"), STATIONS("stations");

    private String type;

    private AjaxRequestType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public String value(){
        return type;
    }
}
