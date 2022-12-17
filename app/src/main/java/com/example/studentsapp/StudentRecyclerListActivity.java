package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.studentsapp.adapters.StudentRecyclerAdapter;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentRecyclerListActivity extends AppCompatActivity {
    private List<Student> students;
    private Button newStudentButton;
    private StudentRecyclerAdapter studentRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        this.students = Model.instance().getAllStudents();
        RecyclerView studentsList = findViewById(R.id.studentrecycler_list);
        studentsList.setHasFixedSize(true);
        studentsList.setLayoutManager(new LinearLayoutManager(this));
        this.studentRecyclerAdapter = new StudentRecyclerAdapter(getLayoutInflater(), this.students);
        studentsList.setAdapter(studentRecyclerAdapter);
        this.studentRecyclerAdapter.setOnItemClickListener(position -> {
            Log.d("TAG", "Row was clicked " + position);
            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra("StudentIndex", position);
            startActivity(intent);
        });
        this.newStudentButton = findViewById(R.id.studentrecycler_list_addstudent_btn);
        this.newStudentButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentFormActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.students = Model.instance().getAllStudents();
        this.studentRecyclerAdapter.notifyDataSetChanged();
    }
}