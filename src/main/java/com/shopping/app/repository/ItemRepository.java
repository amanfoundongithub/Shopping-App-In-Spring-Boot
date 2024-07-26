package com.shopping.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shopping.app.model.Item;
import com.shopping.app.utils.ItemType;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String>{

    @Query("{'sellerId' : ?0}")
    public List<Item> listOfSellerItems(String sellerId);

    @Query("{'itemName' : { $regex: ?0, $options: 'i' }}")
    public List<Item> searchByName(String query);

    @Query("{'itemName' : { $regex: ?0, $options: 'i' }, 'itemType' : ?1}")
    public List<Item> searchByName(String query, ItemType itemType);
    
}
