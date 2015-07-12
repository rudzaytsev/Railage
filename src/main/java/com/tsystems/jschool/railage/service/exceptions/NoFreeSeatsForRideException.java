package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 12.07.15.
 */
public class NoFreeSeatsForRideException extends Exception {

    private String errorMsg;

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public NoFreeSeatsForRideException(String message) {
        super(message);
        errorMsg = message;
    }

    public NoFreeSeatsForRideException() {
        super();
    }
}
