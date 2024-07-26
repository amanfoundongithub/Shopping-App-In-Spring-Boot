package com.shopping.app.exception;

/**
 * Exception when an application fails to perform a given task 
 * 
 * @author amanfoundongithub 
 * 
 **/
public class ApplicationException extends Exception {

    /**
     * Default Application Exception 
     * 
     * @author amanfoundongithub
     * 
     **/
    public ApplicationException() {
        super("Unexpected error in Application");
    }

    /**
     * Defines Application Exception with the message of the possible exception
     * 
     * @param e : Exception 
     */
    public ApplicationException(Exception e) {
        super(e);
    }
    
}
