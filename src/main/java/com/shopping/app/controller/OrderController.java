package com.shopping.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.DeleteException;
import com.shopping.app.exception.ResetException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.exception.UpdateException;
import com.shopping.app.model.Order;
import com.shopping.app.repository.OrderRepository;
import com.shopping.app.utils.OrderStatus;
import com.shopping.app.utils.model.Date;

import java.time.LocalDate;

@Component
public class OrderController implements ControllerInterface<Order>{

    @Autowired
    private OrderRepository orderRepository;

    public OrderController() {
        
    }

    @Override
    public void reset() throws ResetException {
        try {
            orderRepository.deleteAll();
        } catch(Exception e) {
            throw new ResetException(e);
        }
    }

    @Override
    public String create(Order object) throws CreateException {
        try {
            Order createdObject = orderRepository.save(object);

            return createdObject.getId();
        } catch(Exception e) {
            throw new CreateException(e);
        }
        
    }

    @Override
    public boolean update(Order object) throws UpdateException {
        try {
            Order target = orderRepository.findById(object.getId()).get();

            if(target == null){
                throw new UpdateException("Invalid ID, order not found");
            }

            // Set values
            if(object.getCustomerId() != null){
                target.setCustomerId(object.getCustomerId());
            }

            if(object.getItem() != null){
                target.setItem(object.getItem());
            }

            if(object.getOrderStatus() != null){
                if(object.getOrderStatus() == OrderStatus.DELIVERED){
                    LocalDate localDate = LocalDate.now();
                    Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
                    target.setDeliveryDate(date);
                }
                target.setOrderStatus(object.getOrderStatus());
            }

            if(object.getDeliveryLocation() != null){
                target.setDeliveryLocation(object.getDeliveryLocation());
            }

            orderRepository.save(target);

            return true;
        } catch(Exception e) {
            throw new UpdateException(e);
        }
       
    }

    @Override
    public boolean delete(Order object) throws DeleteException {
        try {
            orderRepository.delete(object);
            return true;
        } catch(Exception e) {
            throw new DeleteException(e);
        }
    }

    @Override
    public Order searchByID(String id) throws SearchException {
        try {
            return orderRepository.findById(id).get();
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    @Override
    public List<Order> getAllObjects() throws SearchException {
        try {
            return orderRepository.findAll();
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    // ORDER SPECIFIC DETAILS

    // Get order by seller ID
    public List<Order> getAllOrdersBySellerID(String sellerId) throws SearchException {
        try {
            return orderRepository.findBySellerId(sellerId);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    // Get order by customer ID
    public List<Order> getAllOrdersByCustomerID(String customerId) throws SearchException {
        try {
            return orderRepository.findByCustomerId(customerId);
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }
    
}
