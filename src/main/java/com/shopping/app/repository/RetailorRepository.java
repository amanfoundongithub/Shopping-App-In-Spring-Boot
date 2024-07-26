package com.shopping.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.app.model.Retailor;

public interface RetailorRepository extends MongoRepository<Retailor, String>{
    
    
    @Query("{'name' : { $regex: ?0, $options: 'i' }, 'password' : ?1}")
    public Retailor findRetailorNameAndPass(String name, String password);

}
