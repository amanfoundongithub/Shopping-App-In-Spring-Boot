package com.shopping.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.app.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String>{
    
    @Query("{'customerId' : ?0}")
    public Cart findByCustomerID(String customerId);
}
