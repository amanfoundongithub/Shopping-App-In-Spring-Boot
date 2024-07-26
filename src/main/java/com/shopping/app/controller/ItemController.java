package com.shopping.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.DeleteException;
import com.shopping.app.exception.ResetException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.exception.UpdateException;
import com.shopping.app.model.Item;
import com.shopping.app.repository.ItemRepository;
import com.shopping.app.utils.ItemType;

import java.util.List;

@Component
public class ItemController implements ControllerInterface<Item> {
    
    @Autowired
    private ItemRepository itemRepository;

    public ItemController() {

    }

    @Override
    public void reset() throws ResetException {
        try {
            itemRepository.deleteAll();
        } catch(Exception e){
            throw new ResetException(e);
        }
        
    }

    @Override
    public String create(Item object) throws CreateException {
        try {
            return itemRepository.save(object).getId();
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }



    @Override
    public boolean update(Item object) throws UpdateException {
        try {
            Item target = itemRepository.findById(object.getId()).get();

            if(target == null){
                throw new UpdateException("Invalid ID, item not found");
            }

            if(object.getItemName() != null){
                target.setItemName(object.getItemName());
            }

            if(object.getItemType() != null){
                target.setItemType(object.getItemType());
            }

            if(object.getDescription() != null){
                target.setDescription(object.getDescription());
            }

            if(object.getPrice() > 0){
                target.setPrice(object.getPrice());
            }

            if(object.getQuantity() >= 0){
                target.setQuantity(object.getQuantity());
            }

            itemRepository.save(target);

            return true;
        } catch(Exception e) {
            throw new UpdateException(e);
        }
        
    }



    @Override
    public boolean delete(Item object) throws DeleteException {
        try {
            itemRepository.delete(object);
            return true;
        } catch(Exception e) {
            throw new DeleteException(e);
        }
        
    }


    @Override
    public Item searchByID(String id) throws SearchException {
        try { 
           return itemRepository.findById(id).get(); 
        } catch(Exception e) {
            throw new SearchException(e);
        }
        
    }

    @Override
    public List<Item> getAllObjects() throws SearchException {
        try {
            return itemRepository.findAll();
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public List<Item> getAllItemsBySellerId(String sellerId) throws SearchException {
        try {
            return itemRepository.listOfSellerItems(sellerId);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public List<Item> searchItems(String query) throws SearchException {
        try {
            return itemRepository.searchByName(query);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public List<Item> searchItems(String query, ItemType itemType) throws SearchException {
        try {
            return itemRepository.searchByName(query, itemType);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

}
