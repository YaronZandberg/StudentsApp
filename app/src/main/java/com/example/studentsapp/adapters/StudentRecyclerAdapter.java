package com.example.studentsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.monitoring.OnItemClickListener;
import com.example.studentsapp.viewholders.StudentViewHolder;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<Student> students;
    private OnItemClickListener listener;

    public StudentRecyclerAdapter(LayoutInflater layoutInflater,
                                  List<Student> students) {
        this.layoutInflater = layoutInflater;
        this.students = students;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.student_list_row, parent, false);
        return (new StudentViewHolder(view, this.listener, this.students));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int position) {
        Student student = this.students.get(position);
        studentViewHolder.bindStudent(student, position);
    }

    @Override
    public int getItemCount() {
        return (this.students.size());
    }
}
