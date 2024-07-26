package com.shopping.app.exception;

public class UpdateException extends Exception {
    
    public UpdateException(Exception e){
        super(e);
    }

    public UpdateException(){
        super("Error in Updating");
    }

    public UpdateException(String message) {
        super(message);
    }

}
