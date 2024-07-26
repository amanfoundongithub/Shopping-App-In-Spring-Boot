package com.shopping.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.app.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String>{
    
    @Query("{'email' : ?0, 'password' : ?1}")
    public Customer findCustomerByEmailAndPassword(String email, String password);

    @Query("{'email' : ?0}")
    public Optional<Customer> findByEmail(String email);
}
