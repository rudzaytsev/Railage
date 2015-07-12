package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 12.07.15.
 */
public class InvalidBoardingStationInRouteException extends Exception {

    private String errorMsg;

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public InvalidBoardingStationInRouteException(String message) {
        super(message);
        this.errorMsg = message;
    }
}
