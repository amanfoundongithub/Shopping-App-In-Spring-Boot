package com.shopping.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.model.Customer;
import com.shopping.app.model.Retailor;
import com.shopping.app.repository.CustomerRepository;
import com.shopping.app.repository.RetailorRepository;
import com.shopping.app.utils.model.Location;
import com.shopping.app.utils.request.RetailorLoginRequest;
import com.shopping.app.utils.service.PasswordService;


@Component
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RetailorRepository retailorRepository;

    
    private PasswordService passwordService = new PasswordService();

    public CustomerController(){

    }

    public boolean logIn(String email, String password) {
        // Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        Customer customer = customerRepository.findByEmail(email).get();
        if(customer == null){
            return false;
        }

        if(passwordService.checkPassword(password, customer.getPassword()) == false){
            return false;
        }
        return true;
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email).get();
    }

    public Customer getCustomerByID(String id){
        return customerRepository.findById(id).get();
    }

    public String createAccount(Customer customer) throws CreateException {
        try {
            customer.setPassword(passwordService.hashPassword(customer.getPassword()));
            String id = customerRepository.save(customer).getId();
            return id;
        } catch(Exception e) {
            throw new CreateException(e);
        }
        
    }

    public boolean updateAccount(Customer customer) throws CreateException {
        try {
            Customer target = customerRepository.findById(customer.getId()).get();
            if(customer.getName() != null){
                target.setName(customer.getName());
            }
            if(customer.getDateOfBirth() != null){
                target.setDateOfBirth(customer.getDateOfBirth());
            }
            if(customer.getGender() != null){
                target.setGender(customer.getGender());
            }

            customerRepository.save(target);
            return true;
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    public boolean updateAddress(String id, Location location) throws CreateException {
        try {
            Customer customer = customerRepository.findById(id).get();
            if(customer == null){
                return false;
            }
            customer.setAddress(location);
                
            customerRepository.save(customer);
            return true;
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    public String createRetailor(Retailor retailor) throws CreateException {
        try {
            retailor.setPassword(passwordService.hashPassword(retailor.getPassword()));
            return retailorRepository.save(retailor).getId();
        } catch(Exception e) {
            throw new CreateException(e);
        }
        
    }

    public String verifyRetailor(RetailorLoginRequest retailorLoginRequest) throws SearchException {
        try {
            String name = retailorLoginRequest.name;
            String password = retailorLoginRequest.password;

            Retailor retailor = retailorRepository.findByName(name).get();
            if(retailor == null){
                return "";
            }

            if(passwordService.checkPassword(password, retailor.getPassword()) == false){
                return "";
            }
           
            return retailor.getId();
            
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public Retailor getRetailor(String id) throws SearchException {
        try {
            return retailorRepository.findById(id).get();
        } catch(Exception e) { 
            throw new SearchException(e);
        }
    }
    
}
