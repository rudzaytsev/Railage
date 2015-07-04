package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 04.07.15.
 */
public class DomainObjectAlreadyExistsException extends Exception {

    private String errorMsg;

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public DomainObjectAlreadyExistsException() {
        super();
    }

    public DomainObjectAlreadyExistsException(String message) {
        super(message);
        errorMsg = message;
    }
}
