package com.shopping.app.exception;

public class PaymentException extends Exception {

    public PaymentException(Exception e) {
        super(e);
    }

    public PaymentException(String message) {
        super(message);
    }
    
}
