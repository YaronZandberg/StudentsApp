package com.example.studentsapp.model;

public class Student {
    private String name;
    private String id;
    private String imageUrl;
    private boolean checkBox;

    public Student(String name, String id, String imageUrl, Boolean checkBox) {
        this.name = name;
        this.id = id;
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
