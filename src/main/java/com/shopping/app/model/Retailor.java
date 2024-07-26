package com.shopping.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shopping.app.utils.model.Date;
import com.shopping.app.utils.model.Location;

@Document("retailor")
public class Retailor {

    @Id
    private String id;

    private String name;
    private String password;
    private Date joiningDate;

    private int noOfReviews;
    private float totalRating;

    private Location address;

    // Default constructor
    public Retailor() {
        this.totalRating = 0;
        this.noOfReviews = 0;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public void setPassword(String password) { 
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
