package com.example.smd_restaurant;

public class restaurant_model {
    String name,category,phone,location,description;
    int rating;

    public restaurant_model(String name,String category, String phone,String location, String description, int rating)
    {
        this.name=name;
        this.category=category;
        this.phone=phone;
        this.location=location;
        this.description=description;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
