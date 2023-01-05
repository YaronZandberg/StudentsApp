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
import com.example.studentsapp.databinding.FragmentStudentEditBinding;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentEditFragment extends Fragment {
    private FragmentStudentEditBinding viewBindings;
    private Integer studentPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.studentPosition = StudentEditFragmentArgs.fromBundle(getArguments()).getStudentPosition();
        FragmentActivity parentActivity = getActivity();
        parentActivity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.clear();
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        }, this, Lifecycle.State.RESUMED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBindings = FragmentStudentEditBinding.inflate(inflater, container, false);
        displayStudentDetails();
        this.viewBindings.studenteditFragmentCancelBtn.setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());
        this.viewBindings.studenteditFragmentSaveBtn.setOnClickListener(view -> {
            updateStudent();
            Navigation.findNavController(view).popBackStack();
        });
        this.viewBindings.studenteditFragmentDeleteBtn.setOnClickListener(view -> {
            deleteStudent();
            Navigation.findNavController(view).popBackStack(R.id.studentListFragment, false);
        });
        return this.viewBindings.getRoot();
    }

    private void displayStudentDetails() {
        Student student = Model.instance().getStudentById(this.studentPosition);
        this.viewBindings.studenteditFragmentNameInputEt.setText(student.getName());
        this.viewBindings.studenteditFragmentIdInputEt.setText(student.getId());
        this.viewBindings.studenteditFragmentPhoneInputEt.setText(student.getPhone());
        this.viewBindings.studenteditFragmentAddressInputEt.setText(student.getAddress());
        this.viewBindings.studenteditFragmentCheckbox.setChecked(student.isCheckBox());
    }

    private void deleteStudent() {
        Model.instance().removeStudent(this.studentPosition);
    }

    private void updateStudent() {
        Student newStudent = buildNewStudent();
        Model.instance().setStudent(this.studentPosition, newStudent);
    }

    private Student buildNewStudent() {
        String name = this.viewBindings.studenteditFragmentNameInputEt.getText().toString();
        String id = this.viewBindings.studenteditFragmentIdInputEt.getText().toString();
        String phone = this.viewBindings.studenteditFragmentPhoneInputEt.getText().toString();
        String address = this.viewBindings.studenteditFragmentAddressInputEt.getText().toString();
        boolean checkbox = this.viewBindings.studenteditFragmentCheckbox.isChecked();
        return new Student(name, id, phone, address, "", checkbox);
    }
}