package com.tsystems.jschool.railage.view.controllers.rest.messages.basic;

/**
 * Created by rudolph on 11.08.15.
 */
public interface ResponseMessage<T> {

    public String getResponseForCommand();

    public void setResponseForCommand(String command);

    public String getMessage();

    public void setMessage(String message);

    public T getData();

    public void setData(T data);
}
