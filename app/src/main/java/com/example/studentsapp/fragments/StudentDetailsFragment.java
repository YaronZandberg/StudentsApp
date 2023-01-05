package com.example.studentsapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsapp.R;
import com.example.studentsapp.databinding.FragmentStudentDetailsBinding;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentDetailsFragment extends Fragment {
    private FragmentStudentDetailsBinding viewBindings;
    private Integer studentPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.studentPosition = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentPosition();
        FragmentActivity parentActivity = getActivity();
        parentActivity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.removeItem(R.id.studentNewFragment);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                /*StudentDetailsFragmentDirections
                        .ActionStudentDetailsFragmentToStudentEditFragment action =
                        StudentDetailsFragmentDirections
                        .actionStudentDetailsFragmentToStudentEditFragment(studentPosition);
                Navigation.findNavController(*//*viewBindings.getRoot()*//*).navigate(action);*/
                return true;
            }
        }, this, Lifecycle.State.RESUMED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBindings = FragmentStudentDetailsBinding.inflate(inflater, container, false);
        displayStudentDetails();
        this.viewBindings.studentdetailsFragmentEditBtn.setOnClickListener(view -> {
            StudentDetailsFragmentDirections
                    .ActionStudentDetailsFragmentToStudentEditFragment action =
                    StudentDetailsFragmentDirections
                            .actionStudentDetailsFragmentToStudentEditFragment(studentPosition);
            Navigation.findNavController(view).navigate(action);
        });
        return this.viewBindings.getRoot();
    }

    private void displayStudentDetails() {
        Student student = Model.instance().getStudentById(this.studentPosition);
        this.viewBindings.studentdetailsFragmentNameTvValue.setText(student.getName());
        this.viewBindings.studentdetailsFragmentIdTvValue.setText(student.getId());
        this.viewBindings.studentdetailsFragmentPhoneTvValue.setText(student.getPhone());
        this.viewBindings.studentdetailsFragmentAddressTvValue.setText(student.getAddress());
        this.viewBindings.studentdetailsFragmentCheckbox.setChecked(student.isCheckBox());
    }
}