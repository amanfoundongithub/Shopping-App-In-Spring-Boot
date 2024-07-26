package com.shopping.app.utils.model;

/**
 * Location object
 */
public class Location {

    private String street;
    private String landmark;
    private String nation;
    private String state;
    private String city;
    private int pinCode;

    // Default constructor
    public Location() {
        this.landmark = "";
        this.nation = "India";
        this.street = "";
        this.state = "Rajasthan";
        this.city = "Patna";
        this.pinCode = 0;
    }

    // Getter and Setter methods
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
