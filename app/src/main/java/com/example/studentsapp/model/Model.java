package com.example.studentsapp.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final Integer STUDENTS_AMOUNT = 20;
    private static final Model modelInstance = new Model();

    private Model() {
        for (int i = 0; i < this.STUDENTS_AMOUNT; i++) {
            this.addStudent(new Student("name " + i, "" + i, "03-" + i,
                    "street " + i, "", false));
        }
    }

    public static Model instance() {
        return modelInstance;
    }

    List<Student> data = new ArrayList();

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student student) {
        data.add(student);
    }

    public void setStudent(Integer index, Student student){
        data.set(index, student);
    }

    public void removeStudent(Integer index) {
        data.remove(data.get(index));
    }
}
