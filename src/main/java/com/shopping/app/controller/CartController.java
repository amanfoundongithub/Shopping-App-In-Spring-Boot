package com.shopping.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.DeleteException;
import com.shopping.app.exception.ResetException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.exception.UpdateException;
import com.shopping.app.model.Cart;
import com.shopping.app.repository.CartRepository;
import com.shopping.app.utils.model.CartItem;

@Component
public class CartController implements ControllerInterface<Cart>{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void reset() throws ResetException {
        try {
            cartRepository.deleteAll();
        } catch(Exception e) {
            throw new ResetException(e);
        }
    }

    @Override
    public String create(Cart object) throws CreateException {
        try {
            return cartRepository.save(object).getCustomerId();
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    public String create(String customerId) throws CreateException {
        try {
            Cart cart = new Cart();
            cart.setCustomerId(customerId);
            return cartRepository.save(cart).id;
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    @Override
    public boolean update(Cart object) throws UpdateException {
        try {
            return true;
        } catch(Exception e) {
            throw new UpdateException(e);
        }
    }

    @Override
    public boolean delete(Cart object) throws DeleteException {
        try {
            cartRepository.delete(object);
            return true;
        } catch(Exception e) {
            throw new DeleteException(e);
        }
    }

    public boolean delete(String customerId) throws DeleteException {
        try {
            Cart cart = cartRepository.findByCustomerID(customerId);
            cartRepository.delete(cart);
            return true;
        } catch(Exception e) {
            throw new DeleteException(e);
        }
    }

    @Override
    public Cart searchByID(String id) throws SearchException {
        try {
            return cartRepository.findById(id).get();
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public Cart getCart(String id) throws SearchException{
        try {
            return cartRepository.findByCustomerID(id);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }


    @Override
    public List<Cart> getAllObjects() throws SearchException {
       try {
            return cartRepository.findAll();
       } catch(Exception e) {
        throw new SearchException(e);
       }
    }

    // CART SPECIFIC OPERATIONS
    public boolean addToCart(String id, CartItem cartItem) throws CreateException {
        try {
            Cart cart = cartRepository.findByCustomerID(id);
            boolean added =cart.addToCart(cartItem);

            cartRepository.save(cart);
            return added;
        } catch(Exception e){
            throw new CreateException(e);
        }
    }

    public boolean updateToCart(String id, CartItem cartItem) throws CreateException {
        try {
            Cart cart = cartRepository.findByCustomerID(id);
            boolean added =cart.updateToCart(cartItem);

            cartRepository.save(cart);
            return added;
        } catch(Exception e){
            throw new CreateException(e);
        }
    }

    public boolean deleteToCart(String id, CartItem cartItem) throws CreateException {
        try {
            Cart cart = cartRepository.findByCustomerID(id);
            boolean added =cart.deleteToCart(cartItem);

            cartRepository.save(cart);
            return added;
        } catch(Exception e){
            throw new CreateException(e);
        }
    }

    public boolean deleteToCart(String id, String itemId) throws CreateException {
        try {
            Cart cart = cartRepository.findByCustomerID(id);
            boolean added =cart.deleteToCart(itemId);

            cartRepository.save(cart);
            return added;
        } catch(Exception e){
            throw new CreateException(e);
        }
    }

}
