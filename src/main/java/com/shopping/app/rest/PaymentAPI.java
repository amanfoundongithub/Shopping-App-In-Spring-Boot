package com.shopping.app.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.app.exception.PaymentException;
import com.shopping.app.payment.CreditCard;
import com.shopping.app.payment.DebitCard;
import com.shopping.app.payment.PaymentGateway;
import com.shopping.app.payment.UPI;


@Controller
public class PaymentAPI {
    
    private PaymentGateway paymentGateway = new PaymentGateway();

    public PaymentAPI() {

    }

    @PostMapping("/api/pay/creditcard")
    public ResponseEntity<String> payCC(
        @RequestBody CreditCard creditCard,
        @RequestParam(name = "amount") float amount
    ) throws PaymentException {
        try {
            paymentGateway.setPaymentMethod(creditCard);
            boolean paymentDone = paymentGateway.pay(amount);
            System.out.println(paymentDone);
            String response = "fail";
            if(paymentDone){
                response = "success";
            }

            System.out.println(response);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new PaymentException(e);
        }
    }

    @PostMapping("/api/pay/debitcard")
    public ResponseEntity<String> payDC(
        @RequestBody DebitCard debitCard,
        @RequestParam(name = "amount") float amount
    )  throws PaymentException {
        try {
            paymentGateway.setPaymentMethod(debitCard);
            boolean paymentDone = paymentGateway.pay(amount);
            System.out.println(paymentDone);
            String response = "fail";
            if(paymentDone){
                response = "success";
            }

            System.out.println(response);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new PaymentException(e);
        }
    }

    @PostMapping("/api/pay/upi")
    public ResponseEntity<String> payUPI(
        @RequestBody UPI upi,
        @RequestParam(name = "amount") float amount
    )  throws PaymentException {
        try {
            paymentGateway.setPaymentMethod(upi);
            boolean paymentDone = paymentGateway.pay(amount);
            String response = "fail";
            if(paymentDone){
                response = "success";
            }

            System.out.println(response);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new PaymentException(e);
        }
    }

}
