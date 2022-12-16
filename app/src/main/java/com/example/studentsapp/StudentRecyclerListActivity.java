package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.studentsapp.adapters.StudentRecyclerAdapter;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentRecyclerListActivity extends AppCompatActivity {
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        this.students = Model.instance().getAllStudents();
        RecyclerView studentsList = findViewById(R.id.studentrecycler_list);
        studentsList.setHasFixedSize(true);
        studentsList.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter studentRecyclerAdapter =
                new StudentRecyclerAdapter(getLayoutInflater(), this.students);
        studentsList.setAdapter(studentRecyclerAdapter);
        studentRecyclerAdapter.setOnItemClickListener(position ->
                Log.d("TAG", "Row was clicked " + position));
    }
}