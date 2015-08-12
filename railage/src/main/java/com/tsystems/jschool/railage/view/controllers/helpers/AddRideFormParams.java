package com.tsystems.jschool.railage.view.controllers.helpers;

/**
 * Created by rudolph on 29.07.15.
 */
public class AddRideFormParams {

    private String routeId;
    private String rideDate;

    private String ridePrice;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public String getRidePrice() {
        return ridePrice;
    }

    public void setRidePrice(String ridePrice) {
        this.ridePrice = ridePrice;
    }
}
