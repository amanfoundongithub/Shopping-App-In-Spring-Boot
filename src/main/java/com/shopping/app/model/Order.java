package com.shopping.app.model;

import org.springframework.data.annotation.Id;
import com.shopping.app.utils.OrderStatus;
import com.shopping.app.utils.model.Date;
import com.shopping.app.utils.model.Location;

import java.time.LocalDate;

public class Order {

    @Id
    private String id;
    
    private String customerId;

    private Item item;

    private Date orderDate;
    private Date deliveryDate;

    private OrderStatus orderStatus;
    private Location deliveryLocation;

    public Order() {
        this.orderStatus = OrderStatus.PENDING_CONFIRMATION;
        LocalDate localTime = LocalDate.now();
        this.orderDate = new Date(localTime.getDayOfMonth(), localTime.getMonthValue(), localTime.getYear());
        this.deliveryDate = new Date(0, 0, 0);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Location deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
