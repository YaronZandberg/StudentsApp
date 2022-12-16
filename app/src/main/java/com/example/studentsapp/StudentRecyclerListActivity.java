package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.monitoring.OnItemClickListener;

import java.util.List;

public class StudentRecyclerListActivity extends AppCompatActivity {
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        data = Model.instance().getAllStudents();
        RecyclerView studentsList = findViewById(R.id.studentrecycler_list);
        studentsList.setHasFixedSize(true);

        studentsList.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter();
        studentsList.setAdapter(studentRecyclerAdapter);

        studentRecyclerAdapter.setOnItemClickListener(position ->
                Log.d("TAG", "Row was clicked " + position));

    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox checkBox;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            checkBox = itemView.findViewById(R.id.studentlistrow_checkBox);
            checkBox.setOnClickListener(view1 -> {
                Integer pos = Integer.parseInt(String.valueOf(checkBox.getTag()));
                Student currentStudent = data.get(pos);
                currentStudent.setCheckBox(checkBox.isChecked());
            });
            itemView.setOnClickListener(view -> {
                Integer position = getAdapterPosition();
                Log.d("TAG", "row clicked " + position);
                listener.onItemClick(position);
            });
        }

        public void bindStudent(Student student, Integer position) {
            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            checkBox.setChecked(student.isCheckBox());
            checkBox.setTag(position);
        }
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.listener = onItemClickListener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int position) {
            Student student = data.get(position);
            studentViewHolder.bindStudent(student, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}