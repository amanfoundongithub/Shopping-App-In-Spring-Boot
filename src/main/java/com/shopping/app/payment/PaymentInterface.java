package com.shopping.app.payment;

import com.shopping.app.exception.PaymentException;

public interface PaymentInterface {

    public boolean pay(float requestedAmount) throws PaymentException;
     
}
