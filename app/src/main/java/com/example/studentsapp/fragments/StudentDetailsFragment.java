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
    private Student student;
    // --- Test --- //

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.studentPosition = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentPosition();
        FragmentActivity parentActivity = getActivity();
        parentActivity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.removeItem(R.id.menu_addStudent);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                /*StudentDetailsFragmentDirections
                        .ActionStudentDetailsFragmentToStudentFormFragment action =
                        StudentDetailsFragmentDirections
                        .actionStudentDetailsFragmentToStudentFormFragment(studentPosition);
                Navigation.findNavController(*//* Need a View *//*).navigate(action);*/
                return true;
            }
        }, this, Lifecycle.State.RESUMED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBindings = FragmentStudentDetailsBinding.inflate(inflater, container, false);
        this.student = Model.instance().getStudentById(this.studentPosition);
        displayStudentDetails();
        this.viewBindings.studentdetailsFragmentEditBtn.setOnClickListener(view -> {
            StudentDetailsFragmentDirections
                    .ActionStudentDetailsFragmentToStudentFormFragment action =
                    StudentDetailsFragmentDirections
                            .actionStudentDetailsFragmentToStudentFormFragment(this.studentPosition);
            Navigation.findNavController(view).navigate(action);
        });
        return this.viewBindings.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.student = Model.instance().getStudentById(this.studentPosition);
        displayStudentDetails();
    }

    private void displayStudentDetails() {
        this.viewBindings.studentdetailsFragmentNameInputEt.setText(student.getName());
        this.viewBindings.studentdetailsFragmentNameInputEt.setFocusable(false);
        this.viewBindings.studentdetailsFragmentIdInputEt.setText(student.getId());
        this.viewBindings.studentdetailsFragmentIdInputEt.setFocusable(false);
        this.viewBindings.studentdetailsFragmentPhoneInputEt.setText(student.getPhone());
        this.viewBindings.studentdetailsFragmentPhoneInputEt.setFocusable(false);
        this.viewBindings.studentdetailsFragmentAddressInputEt.setText(student.getAddress());
        this.viewBindings.studentdetailsFragmentAddressInputEt.setFocusable(false);
        this.viewBindings.studentdetailsFragmentCheckbox.setChecked(student.isCheckBox());
        this.viewBindings.studentdetailsFragmentCheckbox.setClickable(false);
        displayDate();
        this.viewBindings.studentdetailsFragmentBirthDateInputEt.setFocusable(false);
        displayTime();
        this.viewBindings.studentdetailsFragmentBirthTimeInputEt.setFocusable(false);
    }

    private void displayDate() {
        String displayedDate = this.student.getBirthDateDay() + "/" +
                this.student.getBirthDateMonth() + "/" + this.student.getBirthDateYear();
        this.viewBindings.studentdetailsFragmentBirthDateInputEt.setText(displayedDate);
    }

    private void displayTime() {
        String displayedTime = this.student.getBirthDateHour() + ":" + this.student.getBirthDateMinute();
        this.viewBindings.studentdetailsFragmentBirthTimeInputEt.setText(displayedTime);
    }
}