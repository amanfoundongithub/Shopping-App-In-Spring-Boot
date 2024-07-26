package com.shopping.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.app.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
    
    @Query("{ 'item.sellerId': ?0 }")
    List<Order> findBySellerId(String sellerId);

    @Query("{'customerId' : ?0}")
    List<Order> findByCustomerId(String customerId);
}
