package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;
import java.util.Objects;

public class StudentFormActivity extends AppCompatActivity {
    private List<Student> students;
    private Button cancelButton;
    private Button deleteButton;
    private Button saveButton;
    private EditText nameEt;
    private EditText idEt;
    private EditText phoneEt;
    private EditText addressEt;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        attachAllElements();
        this.students = Model.instance().getAllStudents();
        Integer studentIndex = getIntent().getIntExtra("StudentIndex", -1);
        final Student currentStudent = studentIndex > -1 ? this.students.get(studentIndex) : null;
        if (studentIndex < 0){
            this.deleteButton.setVisibility(View.INVISIBLE);
        } else {
            displayStudentDetails(currentStudent);
            this.deleteButton.setOnClickListener(view -> {
                Model.instance().removeStudent(studentIndex);
                finish();
            });
        }

        this.cancelButton.setOnClickListener(view -> finish());
        /*this.saveButton.setOnClickListener(view -> {
            Student newStudent = new Student(this.nameEt.getText().toString(),
                    this.idEt.getText().toString(),
                    this.phoneEt.getText().toString(),
                    this.addressEt.getText().toString(),
                    "",
                    this.checkBox.isChecked());
            if (Objects.isNull(currentStudent)) {
                Model.instance().addStudent(newStudent);
            } else {
                Model.instance().setStudent(studentIndex, newStudent);
            }
            finish();
        });*/
    }

    public void displayStudentDetails(Student student){
        this.nameEt.setText(student.getName());
        this.idEt.setText(student.getId());
        this.phoneEt.setText(student.getPhone());
        this.addressEt.setText(student.getAddress());
        this.checkBox.setChecked(student.isCheckBox());
    }

    public void attachAllElements(){
        this.cancelButton = findViewById(R.id.newstudent_cancel_btn);
        this.deleteButton = findViewById(R.id.newstudent_delete_btn);
        this.saveButton = findViewById(R.id.newstudent_save_btn);
        this.nameEt = findViewById(R.id.newstudent_name_et);
        this.idEt = findViewById(R.id.newstudent_id_et);
        this.phoneEt = findViewById(R.id.newstudent_phone_et);
        this.addressEt = findViewById(R.id.newstudent_address_et);
        this.checkBox = findViewById(R.id.newstudent_checkbox);
    }
}