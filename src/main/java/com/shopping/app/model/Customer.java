package com.shopping.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shopping.app.utils.Gender;
import com.shopping.app.utils.model.Date;
import com.shopping.app.utils.model.Location;
import com.shopping.app.utils.model.Name;

import java.time.LocalDate;
import java.time.Period;

/**
 * Customer object
 * 
 * @author amanfoundongithub
 * 
 */
@Document("customer")
public class Customer {

    @Id
    private String id;

    private Name name;
    private String email;
    private String password;
    private Gender gender;
    private Date dateOfBirth;
    private Date dateOfJoining;

    private Location address;

    // No-args constructor
    public Customer() {
        this.address = new Location();
    }

    // All-args constructor
    public Customer(String id, Name name, String email, String password, Gender gender, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for name
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter for gender
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Getter and setter for dateOfBirth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        LocalDate birthDate = LocalDate.of(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDate());

        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();
    }

    // Getter and setter for joining
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }
}
