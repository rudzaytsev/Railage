package com.tsystems.javaschool;

/**
 * Created by rudolph on 13.08.15.
 */
public class PostResponseData {

   String responseForCommand;
   String message;
   String data;

    public String getResponseForCommand() {
        return responseForCommand;
    }

    public void setResponseForCommand(String responseForCommand) {
        this.responseForCommand = responseForCommand;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
