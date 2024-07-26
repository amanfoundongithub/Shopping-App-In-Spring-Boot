package com.shopping.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.model.Customer;
import com.shopping.app.repository.CustomerRepository;
import com.shopping.app.utils.model.Location;


@Component
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

  

    public CustomerController(){

    }

    public boolean logIn(String email, String password) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);

        if(customer != null){
            return true;
        }
        return false;
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email).get();
    }

    public Customer getCustomerByID(String id){
        return customerRepository.findById(id).get();
    }

    public String createAccount(Customer customer) throws CreateException {
        try {
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
    
}
