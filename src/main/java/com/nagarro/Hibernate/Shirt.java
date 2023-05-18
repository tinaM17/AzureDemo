package com.nagarro.Hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TShirt")
public class Shirt {

	@Id
    private String id;
    private String name;
    private String colour;
    private String gender;
    private String size;
    private float price;
    private float rating;
    private String available;

    public Shirt()
    {
    	
    }
    
    public Shirt(String id, String name, String colour, String gender, String size, float price, float rating,
                 String available) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.gender = gender;
        this.size = size;
        this.price = price;
        this.rating = rating;
        this.available = available;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getColour() {
        return colour;
    }
    public String getGender() {
        return gender;
    }
    public String getSize() {
        return size;
    }
    public float getPrice() {
        return price;
    }
    public float getRating() {
        return rating;
    }
    public String getAvailable() {
        return available;
    }
}
