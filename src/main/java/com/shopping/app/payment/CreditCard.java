package com.shopping.app.payment;

import com.shopping.app.exception.PaymentException;
import java.time.LocalDate;


public class CreditCard implements PaymentInterface {

    public String cardNumber;
    public int cvv;

    public String cardHolder;
    public int monthExpire;
    public int yearExpire;

    public CreditCard(String cardNumber, String cardHolder, int cvv, int monthExpire, int yearExpire) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.monthExpire = monthExpire;
        this.yearExpire = yearExpire;
    }

    public CreditCard() {

    }

    // Validate credit card
    public boolean validateCard(){
        LocalDate localDate = LocalDate.now();
        if(this.yearExpire < localDate.getYear()){
            return false;
        }
        if(this.yearExpire == localDate.getYear() && this.monthExpire < localDate.getMonthValue()){
            return false;
        }

        if(cardNumber.length() != 12){
            return false;
        }
        
        return true;
    }

    @Override
    public boolean pay(float requestedAmount) throws PaymentException {
        try {
            if(validateCard() == false){
                return false;
            }

            // PAYMENT PROCEDURE HERE 

            return true;
            
        } catch(Exception e) {
            throw new PaymentException(e);
        }
    }
    
}
