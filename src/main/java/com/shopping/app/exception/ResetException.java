package com.shopping.app.exception;


public class ResetException extends Exception {

    public ResetException(Exception e) {
        super(e);
    }

    public ResetException(String message) {
        super(message);
    }
    
}
