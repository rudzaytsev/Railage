package com.tsystems.javaschool.exceptions;

/**
 * Created by rudolph on 14.08.15.
 */
public class ErrorResponseException extends Exception {

    String message;

    public ErrorResponseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
