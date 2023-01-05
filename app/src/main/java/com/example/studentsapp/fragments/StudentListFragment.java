package com.example.studentsapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsapp.R;
import com.example.studentsapp.adapters.StudentRecyclerAdapter;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListFragment extends Fragment {
    private List<Student> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity parentActivity = getActivity();
        parentActivity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.removeItem(R.id.studentEditFragment);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        }, this, Lifecycle.State.RESUMED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_student_list, container, false);
        this.data = Model.instance().getAllStudents();
        RecyclerView studentsList = fragmentView.findViewById(R.id.studentslistfragment_list);
        studentsList.setHasFixedSize(true);
        studentsList.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(getLayoutInflater(), data);
        studentsList.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            StudentListFragmentDirections
                    .ActionStudentListFragmentToStudentDetailsFragment action =
                    StudentListFragmentDirections
                            .actionStudentListFragmentToStudentDetailsFragment(position);
            Navigation.findNavController(fragmentView).navigate(action);
        });

        return fragmentView;
    }
}