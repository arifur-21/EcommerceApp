package com.example.foodproject.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddProductModel {

    private String name,price,description,imageurl,id, time,date;

    public AddProductModel() {
    }

    public AddProductModel(String name, String price, String description, String imageurl, String id, String time, String date) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageurl = imageurl;
        this.id = id;
        this.time = time;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
