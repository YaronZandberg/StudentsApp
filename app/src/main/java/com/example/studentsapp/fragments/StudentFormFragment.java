package com.example.studentsapp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsapp.R;
import com.example.studentsapp.databinding.FragmentStudentFormBinding;
import com.example.studentsapp.fragments.dialogs.AddStudentDialogFragment;
import com.example.studentsapp.fragments.dialogs.DeleteStudentDialogFragment;
import com.example.studentsapp.fragments.dialogs.UpdateStudentDialogFragment;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentFormFragment extends Fragment {
    private FragmentStudentFormBinding viewBindings;
    private Integer studentPosition;
    private Student student = null;
    private Integer birthDateYear = 2023;
    private Integer birthDateMonth = 1;
    private Integer birthDateDay = 1;
    private Integer birthDateHour = 12;
    private Integer birthDateMinute = 30;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.studentPosition = StudentFormFragmentArgs.fromBundle(getArguments()).getStudentPosition();
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
        this.viewBindings = FragmentStudentFormBinding.inflate(inflater, container, false);
        if (this.studentPosition > -1) {
            this.viewBindings.studentformFragmentDeleteBtn.setVisibility(View.VISIBLE);
            this.student = Model.instance().getStudentById(this.studentPosition);
            initializeParameters();
            displayStudentDetails();
        }
        displayDate();
        displayTime();

        this.viewBindings.studentformFragmentBirthDateInputEt.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                Dialog dialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                    this.birthDateYear = year;
                    this.birthDateMonth = month;
                    this.birthDateDay = day;
                    displayDate();
                }, this.birthDateYear, this.birthDateMonth, this.birthDateDay);
                dialog.show();
                return true;
            }
            return false;
        });

        this.viewBindings.studentformFragmentBirthTimeInputEt.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                Dialog dialog = new TimePickerDialog(getContext(), (timePicker, hour, minute) -> {
                    this.birthDateHour = hour;
                    this.birthDateMinute = minute;
                    displayTime();
                }, this.birthDateHour, this.birthDateMinute, true);
                dialog.show();
                return true;
            }
            return false;
        });

        this.viewBindings.studentformFragmentCancelBtn.setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());
        this.viewBindings.studentformFragmentSaveBtn.setOnClickListener(view -> {
            updateStudent();
            if (this.studentPosition == -1) {
                new AddStudentDialogFragment().show(getActivity().getSupportFragmentManager(), "TAG");
            } else {
                new UpdateStudentDialogFragment().show(getActivity().getSupportFragmentManager(), "TAG");
            }
            Navigation.findNavController(view).popBackStack();
        });
        this.viewBindings.studentformFragmentDeleteBtn.setOnClickListener(view -> {
            deleteStudent();
            new DeleteStudentDialogFragment().show(getActivity().getSupportFragmentManager(), "TAG");
            Navigation.findNavController(view).popBackStack(R.id.studentListFragment, false);
        });
        return this.viewBindings.getRoot();
    }

    private void initializeParameters() {
        this.birthDateYear = this.student.getBirthDateYear();
        this.birthDateMonth = this.student.getBirthDateMonth();
        this.birthDateDay = this.student.getBirthDateDay();
        this.birthDateHour = this.student.getBirthDateHour();
        this.birthDateMinute = this.student.getBirthDateMinute();
    }

    private void displayStudentDetails() {
        this.viewBindings.studentformFragmentNameInputEt.setText(this.student.getName());
        this.viewBindings.studentformFragmentIdInputEt.setText(this.student.getId());
        this.viewBindings.studentformFragmentPhoneInputEt.setText(this.student.getPhone());
        this.viewBindings.studentformFragmentAddressInputEt.setText(this.student.getAddress());
        this.viewBindings.studentformFragmentCheckbox.setChecked(this.student.isCheckBox());
    }

    private void displayDate() {
        String displayedDate = this.birthDateDay + "/" + this.birthDateMonth + "/" + this.birthDateYear;
        this.viewBindings.studentformFragmentBirthDateInputEt.setText(displayedDate);
    }

    private void displayTime() {
        String displayedTime = this.birthDateHour + ":" + this.birthDateMinute;
        this.viewBindings.studentformFragmentBirthTimeInputEt.setText(displayedTime);
    }

    private void deleteStudent() {
        Model.instance().removeStudent(this.studentPosition);
    }

    private void updateStudent() {
        Student newStudent = buildNewStudent();
        if (this.studentPosition == -1) {
            Model.instance().addStudent(newStudent);
        } else {
            Model.instance().setStudent(this.studentPosition, newStudent);
        }
    }

    private Student buildNewStudent() {
        String name = this.viewBindings.studentformFragmentNameInputEt.getText().toString();
        String id = this.viewBindings.studentformFragmentIdInputEt.getText().toString();
        String phone = this.viewBindings.studentformFragmentPhoneInputEt.getText().toString();
        String address = this.viewBindings.studentformFragmentAddressInputEt.getText().toString();
        boolean checkbox = this.viewBindings.studentformFragmentCheckbox.isChecked();
        return new Student(name, id, phone, address, "", checkbox,
                this.birthDateYear, this.birthDateMonth, this.birthDateDay,
                this.birthDateHour, this.birthDateMinute);
    }
}