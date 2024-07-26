package com.shopping.app.exception;

public class CreateException extends Exception {

    public CreateException(String message){
        super(message);
    }

    public CreateException(Exception e){
        super(e);
    }
    
}
