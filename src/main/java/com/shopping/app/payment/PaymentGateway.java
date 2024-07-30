package com.shopping.app.payment;

import com.shopping.app.exception.PaymentException;

public class PaymentGateway {
    
    private PaymentInterface paymentMethod;

    public PaymentGateway() {

    }

    public void setPaymentMethod(PaymentInterface paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean pay(float requestedAmount) throws PaymentException {
        try {
            if(this.paymentMethod == null){
                throw new PaymentException("Payment Method is NULL");
            }
            return this.paymentMethod.pay(requestedAmount);
        } catch(Exception e){
            throw new PaymentException(e);
        }
    }

}
