package com.tsystems.jschool.railage.domain;

/**
 * Created by rudolph on 30.06.15.
 */
public enum RoutePartStatuses {

    START("start"), END("end"), STAND("stand");

    private String status;

    RoutePartStatuses(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public String value(){
        return status;
    }
}
