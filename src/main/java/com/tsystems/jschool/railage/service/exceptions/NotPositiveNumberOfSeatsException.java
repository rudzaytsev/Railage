package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 04.07.15.
 */
public class NotPositiveNumberOfSeatsException extends Exception {

    private String errorMsg;

    public NotPositiveNumberOfSeatsException() {
    }

    public NotPositiveNumberOfSeatsException(String message) {
        super(message);
        errorMsg = message;
    }

    @Override
    public String getMessage() {
        return  errorMsg;
    }
}
