package com.example.studentsapp.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final Integer STUDENTS_AMOUNT = 20;
    private static final Model modelInstance = new Model();

    private Model() {
        for (int i = 0; i < this.STUDENTS_AMOUNT; i++) {
            this.addStudent(initializeStudent(i));
        }
    }

    public static Model instance() {
        return modelInstance;
    }

    List<Student> data = new ArrayList();

    public List<Student> getAllStudents() {
        return data;
    }

    public Student getStudentById(Integer id) {
        return data.get(id);
    }

    private Student initializeStudent(Integer index) {
        String name = "name " + index;
        String id = "" + index;
        String phone = "03-" + index;
        String address = "street " + index;
        String imageUrl = "";
        boolean checkbox = false;
        Integer year = 2022;
        Integer month = index % 12;
        Integer day = index % 30;
        Integer hour = index % 24;
        Integer minute = index % 60;
        if (minute < 10) {
            minute *= 10;
        }
        if (index == 0) {
            month = 5;
            day = 17;
            hour = 18;
            minute = 34;
        }
        return new Student(name, id, phone, address, imageUrl, checkbox, year, month, day, hour, minute);
    }

    public void addStudent(Student student) {
        data.add(student);
    }

    public void setStudent(Integer index, Student student) {
        data.set(index, student);
    }

    public void removeStudent(Integer index) {
        data.remove(data.get(index));
    }
}
