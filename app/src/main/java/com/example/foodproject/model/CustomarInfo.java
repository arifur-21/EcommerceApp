package com.example.foodproject.model;

public class CustomarInfo {

    private String name,phone,address,gender,city, customarId;

    public CustomarInfo() {
    }

    public CustomarInfo(String name, String phone, String address, String gender, String city, String customarId) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.city = city;
        this.customarId = customarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomarId() {
        return customarId;
    }

    public void setCustomarId(String customarId) {
        this.customarId = customarId;
    }

    @Override
    public String toString() {
        return "CustomarInformation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", customarId='" + customarId + '\'' +
                '}';
    }
}
