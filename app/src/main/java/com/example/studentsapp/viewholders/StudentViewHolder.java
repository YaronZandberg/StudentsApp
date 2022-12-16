package com.example.studentsapp.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.monitoring.OnItemClickListener;

import java.util.List;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final List<Student> students;
    private final TextView nameTv;
    private final TextView idTv;
    private final CheckBox checkBox;

    public StudentViewHolder(@NonNull View itemView,
                             OnItemClickListener listener,
                             List<Student> students) {
        super(itemView);
        this.students = students;
        this.nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
        this.idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
        this.checkBox = itemView.findViewById(R.id.studentlistrow_checkBox);
        this.checkBox.setOnClickListener(view1 -> {
            Integer position = Integer.parseInt(String.valueOf(checkBox.getTag()));
            Student currentStudent = this.students.get(position);
            currentStudent.setCheckBox(checkBox.isChecked());
        });
        itemView.setOnClickListener(view -> {
            Integer position = getAdapterPosition();
            Log.d("TAG", "row clicked " + position);
            listener.onItemClick(position);
        });
    }

    public void bindStudent(Student student, Integer position) {
        this.nameTv.setText(student.getName());
        this.idTv.setText(student.getId());
        this.checkBox.setChecked(student.isCheckBox());
        this.checkBox.setTag(position);
    }
}
