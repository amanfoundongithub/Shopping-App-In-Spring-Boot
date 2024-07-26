package com.shopping.app.exception;

public class DeleteException extends Exception {
    
    public DeleteException(Exception e) {
        super(e);
    }

    public DeleteException() {
        super("Error in Deletion");
    }

    public DeleteException(String message) {
        super(message);
    }
}
