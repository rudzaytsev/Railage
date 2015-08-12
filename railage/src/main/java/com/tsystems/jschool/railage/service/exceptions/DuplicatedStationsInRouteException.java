package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 06.07.15.
 */
public class DuplicatedStationsInRouteException extends Exception {

    private String errorMsg;

    public DuplicatedStationsInRouteException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

}
