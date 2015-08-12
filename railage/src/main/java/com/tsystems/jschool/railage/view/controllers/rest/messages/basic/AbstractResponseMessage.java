package com.tsystems.jschool.railage.view.controllers.rest.messages.basic;

/**
 * Created by rudolph on 11.08.15.
 */
public abstract class AbstractResponseMessage<T> implements ResponseMessage<T> {

    protected String responseForCommand;

    protected String message;

    protected Integer code;

    protected T data;

    @Override
    public String getResponseForCommand() {
        return responseForCommand;
    }

    @Override
    public void setResponseForCommand(String command) {
        this.responseForCommand = command;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}
