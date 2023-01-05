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

import com.example.studentsapp.databinding.FragmentStudentNewBinding;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentNewFragment extends Fragment {
    private FragmentStudentNewBinding viewBindings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBindings = FragmentStudentNewBinding.inflate(inflater, container, false);
        this.viewBindings.studentnewFragmentCancelBtn.setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());
        this.viewBindings.studentnewFragmentSaveBtn.setOnClickListener(view -> {
            saveStudent();
            Navigation.findNavController(view).popBackStack();
        });
        return this.viewBindings.getRoot();
    }

    private void saveStudent() {
        Student newStudent = buildNewStudent();
        Model.instance().addStudent(newStudent);
    }

    private Student buildNewStudent() {
        String name = this.viewBindings.studentnewFragmentNameInputEt.getText().toString();
        String id = this.viewBindings.studentnewFragmentIdInputEt.getText().toString();
        String phone = this.viewBindings.studentnewFragmentPhoneInputEt.getText().toString();
        String address = this.viewBindings.studentnewFragmentAddressInputEt.getText().toString();
        boolean checkbox = this.viewBindings.studentnewFragmentCheckbox.isChecked();
        return new Student(name, id, phone, address, "", checkbox);
    }
}