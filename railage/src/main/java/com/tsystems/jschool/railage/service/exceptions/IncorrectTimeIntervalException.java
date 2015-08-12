package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 10.07.15.
 */
public class IncorrectTimeIntervalException extends Exception {

    private String errorMsg;

    public IncorrectTimeIntervalException(String message) {
        super(message);
        errorMsg = message;
    }

    @Override
    public String getMessage() {
        return  errorMsg;
    }

}
