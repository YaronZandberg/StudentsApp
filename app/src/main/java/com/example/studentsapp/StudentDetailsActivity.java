package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    private List<Student> students;
    private Integer studentsCount;
    private Integer studentIndex;
    private Button editButton;
    private Button cancelButton;
    private TextView nameEt;
    private TextView idEt;
    private TextView phoneEt;
    private TextView addressEt;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        attachAllElements();
        this.students = Model.instance().getAllStudents();
        this.studentsCount = this.students.size();
        this.studentIndex = getIntent().getIntExtra("StudentIndex", -1);
        if (studentIndex > -1){
            Student currentStudent = this.students.get(studentIndex);
            displayStudentDetails(currentStudent);
        }

        this.cancelButton.setOnClickListener(view -> finish());
        this.editButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentFormActivity.class);
            intent.putExtra("StudentIndex", studentIndex);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.studentsCount != Model.instance().getAllStudents().size()) {
            finish();
        } else {
            displayStudentDetails(Model.instance().getAllStudents().get(this.studentIndex));
        }
    }

    public void attachAllElements(){
        this.editButton = findViewById(R.id.studentdetails_edit_btn);
        this.cancelButton = findViewById(R.id.studentdetails_cancel_btn);
        this.nameEt = findViewById(R.id.studentdetails_value_name);
        this.idEt = findViewById(R.id.studentdetails_value_id);
        this.phoneEt = findViewById(R.id.studentdetails_value_phone);
        this.addressEt = findViewById(R.id.studentdetails_value_address);
        this.checkBox = findViewById(R.id.studentdetails_checkbox);
    }

    public void displayStudentDetails(Student student){
        this.nameEt.setText(student.getName());
        this.idEt.setText(student.getId());
        this.phoneEt.setText(student.getPhone());
        this.addressEt.setText(student.getAddress());
        this.checkBox.setChecked(student.isCheckBox());
        this.checkBox.setClickable(false);
    }
}