package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;
import java.util.Objects;

public class StudentListActivity extends AppCompatActivity {
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        data = Model.instance().getAllStudents();
        ListView listView = findViewById(R.id.studentlist_list);
        listView.setAdapter(new StudentListAdapter());
        listView.setOnItemClickListener((adapterView, view, position, l) ->
                Log.d("TAG", "row was clicked " + position));
    }

    class StudentListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (Objects.isNull(view)){
                view = getLayoutInflater().inflate(R.layout.student_list_row, null);
                CheckBox checkBox = view.findViewById(R.id.studentlistrow_checkBox);
                checkBox.setOnClickListener(view1 -> {
                    Integer pos = Integer.parseInt(String.valueOf(checkBox.getTag()));
                    Student currentStudent = data.get(pos);
                    currentStudent.setCheckBox(checkBox.isChecked());
                });
            }
            TextView nameTv = view.findViewById(R.id.studentlistrow_name_tv);
            TextView idTv = view.findViewById(R.id.studentlistrow_id_tv);
            CheckBox checkBox = view.findViewById(R.id.studentlistrow_checkBox);

            Student student = data.get(position);
            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            checkBox.setChecked(student.isCheckBox());
            checkBox.setTag(position);
            return view;
        }
    }
}