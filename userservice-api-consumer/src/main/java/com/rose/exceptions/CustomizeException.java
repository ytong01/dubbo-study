package com.rose.exceptions;

public class CustomizeException extends Exception {

    public CustomizeException(String msg) {
        super(msg);
    }

    public CustomizeException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
