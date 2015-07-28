package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 28.07.15.
 */
public class InvalidUserDataException extends Exception {

    private String errorMsg;

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public InvalidUserDataException(String message){
        super(message);
        errorMsg = message;
    }
}
