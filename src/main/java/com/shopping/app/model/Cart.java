package com.shopping.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shopping.app.utils.model.CartItem;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Document("cart")
public class Cart {

    @Id
    public String id;
    
    private String customerId;

    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {

    }

    public String getCustomerId(){
        return customerId;
    }

    public void setCustomerId(String id){
        this.customerId = id;
    }

    public List<CartItem> getCart(){
        return this.cartItems;
    }

    public boolean addToCart(CartItem cartItem){
        if(findInCart(cartItem.itemCode).isPresent()){
            updateCount(cartItem.itemCode, cartItem.quantity);
            return true;
        }
        else{
            cartItems.add(cartItem);
            return true;
        }
    }

    public boolean updateToCart(CartItem cartItem){
        if(findInCart(cartItem.itemCode).isPresent()){
            updateCount2(cartItem.itemCode, cartItem.quantity);
            return true;
        }
        else{
            cartItems.add(cartItem);
            return true;
        }
    }

    public boolean deleteToCart(CartItem cartItem){
        if(findInCart(cartItem.itemCode).isPresent()){
            deleteInCart(cartItem.itemCode);
        }

        return true;
    }

    public boolean deleteToCart(String itemCode){
        if(findInCart(itemCode).isPresent()){
            deleteInCart(itemCode);
        }

        return true;
    }
    public Optional<CartItem> findInCart(String code){
       return cartItems.stream().filter(item -> item.itemCode.equals(code)).findFirst();
    }

    private void deleteInCart(String code){
        cartItems =  cartItems.stream().filter(item -> item.itemCode.equals(code) == false).collect(Collectors.toList());
     }

    private void updateCount(String code, int increment){
        findInCart(code).ifPresent(item -> item.quantity += increment);
    }

    private void updateCount2(String code, int quantity) {
        findInCart(code).ifPresent(item -> item.quantity = quantity);
    }




}
