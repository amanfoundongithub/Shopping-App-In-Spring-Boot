package com.shopping.app.payment;

import com.shopping.app.exception.PaymentException;

public class UPI implements PaymentInterface {

    public String vpa;

    public UPI(String vpa) {
        this.vpa = vpa;
    }
    public UPI() {
        
    }

    @Override
    public boolean pay(float requestedAmount) throws PaymentException {
        try {
            return true;
        } catch (Exception e) {
            throw new PaymentException(e);
        }
    }

}
