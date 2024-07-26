package com.shopping.app.utils.model;


/**
 * Custom date object 
 * 
 * @author amanfoundongithub
 */
public class Date {
    
    private int date;
    private int month;
    private int year;

    // Constructor
    public Date(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Date() {
        
    }

    // Getter for date
    public int getDate() {
        return date;
    }

    // Setter for date
    public void setDate(int date) {
        this.date = date;
    }

    // Getter for month
    public int getMonth() {
        return month;
    }

    // Setter for month
    public void setMonth(int month) {
        this.month = month;
    }

    // Getter for year
    public int getYear() {
        return year;
    }

    // Setter for year
    public void setYear(int year){
        this.year = year;
    }

    @Override
    public String toString(){
        String date = "";

        if(this.date < 10){
            date = "0" + date;
        }
        
        date = date + this.date;
        if(month< 10){
            date = date + "-0" + month;
        }
        else{
            date = date + "-" + month;
        }

        date = date + "-" + year;

        return date;
    }

    public String toReverseString(){
        String date = "";

        date = date + year;

        date = date + "-";

        if(month< 10){
            date = date + "0" + month;
        }
        else{
            date = date + month;
        }

        date = date + "-";

        if(this.date < 10){
            date = date + "0";
        }
        
        date = date + this.date;
       
        return date;
    }
}