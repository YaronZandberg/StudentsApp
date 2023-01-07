package com.example.studentsapp.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String phone;
    private String address;
    private String imageUrl;
    private boolean checkBox;
    private Integer birthDateYear;
    private Integer birthDateMonth;
    private Integer birthDateDay;
    private Integer birthDateHour;
    private Integer birthDateMinute;

    public Student(String name, String id, String phone, String address, String imageUrl,
                   Boolean checkBox, Integer birthDateYear, Integer birthDateMonth,
                   Integer birthDateDay, Integer birthDateHour, Integer birthDateMinute) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.imageUrl = imageUrl;
        this.checkBox = checkBox;
        this.birthDateYear = birthDateYear;
        this.birthDateMonth = birthDateMonth;
        this.birthDateDay = birthDateDay;
        this.birthDateHour = birthDateHour;
        this.birthDateMinute = birthDateMinute;
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

    public Integer getBirthDateYear() {
        return this.birthDateYear;
    }

    public void setBirthDateYear(Integer birthDateYear) {
        this.birthDateYear = birthDateYear;
    }

    public Integer getBirthDateMonth() {
        return this.birthDateMonth;
    }

    public void setBirthDateMonth(Integer birthDateMonth) {
        this.birthDateMonth = birthDateMonth;
    }

    public Integer getBirthDateDay() {
        return this.birthDateDay;
    }

    public void setBirthDateDay(Integer birthDateDay) {
        this.birthDateDay = birthDateDay;
    }

    public Integer getBirthDateHour() {
        return this.birthDateHour;
    }

    public void setBirthDateHour(Integer birthDateHour) {
        this.birthDateHour = birthDateHour;
    }

    public Integer getBirthDateMinute() {
        return this.birthDateMinute;
    }

    public void setBirthDateMinute(Integer birthDateMinute) {
        this.birthDateMinute = birthDateMinute;
    }
}
