package com.example.foodproject.model;

public class UsersModel {

    private String name, password, email, userId;

    public UsersModel() {
    }

    public UsersModel(String name, String password, String email, String userId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}