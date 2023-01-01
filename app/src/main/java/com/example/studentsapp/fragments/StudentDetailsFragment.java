package com.example.studentsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsapp.databinding.FragmentStudentDetailsBinding;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentDetailsFragment extends Fragment {
    private FragmentStudentDetailsBinding viewBindings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Integer studentPosition = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentPosition();
        this.viewBindings = FragmentStudentDetailsBinding.inflate(inflater, container, false);
        displayStudentDetails(studentPosition);
        return this.viewBindings.getRoot();
    }

    private void displayStudentDetails(Integer studentPosition) {
        Student student = Model.instance().getStudentById(studentPosition);
        this.viewBindings.studentdetailsFragmentNameTvValue.setText(student.getName());
        this.viewBindings.studentdetailsFragmentIdTvValue.setText(student.getId());
        this.viewBindings.studentdetailsFragmentPhoneTvValue.setText(student.getPhone());
        this.viewBindings.studentdetailsFragmentAddressTvValue.setText(student.getAddress());
        this.viewBindings.studentdetailsFragmentCheckbox.setChecked(student.isCheckBox());
    }
}