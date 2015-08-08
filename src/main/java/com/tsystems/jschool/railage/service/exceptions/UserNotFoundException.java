package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 08.08.15.
 */
public class UserNotFoundException extends Exception {

    String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
