package com.example.studentsapp.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String phone;
    private String address;
    private String imageUrl;
    private boolean checkBox;

    public Student(String name, String id, String phone,
                   String address, String imageUrl, Boolean checkBox) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.imageUrl = imageUrl;
        this.checkBox = checkBox;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCheckBox() {
        return this.checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
