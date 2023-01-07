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
import com.example.studentsapp.databinding.FragmentStudentEditBinding;
import com.example.studentsapp.fragments.dialogs.DeleteStudentDialogFragment;
import com.example.studentsapp.fragments.dialogs.UpdateStudentDialogFragment;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentEditFragment extends Fragment {
    private FragmentStudentEditBinding viewBindings;
    private Integer studentPosition;
    private Student student;
    private Integer birthDateYear;
    private Integer birthDateMonth;
    private Integer birthDateDay;
    private Integer birthDateHour;
    private Integer birthDateMinute;

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
        this.student = Model.instance().getStudentById(this.studentPosition);
        initializeParameters();
        displayStudentDetails();

        this.viewBindings.studenteditFragmentBirthDateInputEt.setOnTouchListener((view, motionEvent) -> {
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

        this.viewBindings.studenteditFragmentBirthTimeInputEt.setOnTouchListener((view, motionEvent) -> {
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

        this.viewBindings.studenteditFragmentCancelBtn.setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());
        this.viewBindings.studenteditFragmentSaveBtn.setOnClickListener(view -> {
            updateStudent();
            new UpdateStudentDialogFragment().show(getActivity().getSupportFragmentManager(), "TAG");
            Navigation.findNavController(view).popBackStack();
        });
        this.viewBindings.studenteditFragmentDeleteBtn.setOnClickListener(view -> {
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
        this.viewBindings.studenteditFragmentNameInputEt.setText(this.student.getName());
        this.viewBindings.studenteditFragmentIdInputEt.setText(this.student.getId());
        this.viewBindings.studenteditFragmentPhoneInputEt.setText(this.student.getPhone());
        this.viewBindings.studenteditFragmentAddressInputEt.setText(this.student.getAddress());
        this.viewBindings.studenteditFragmentCheckbox.setChecked(this.student.isCheckBox());
        displayDate();
        displayTime();
    }

    private void displayDate() {
        String displayedDate = this.birthDateDay + "/" + this.birthDateMonth + "/" + this.birthDateYear;
        this.viewBindings.studenteditFragmentBirthDateInputEt.setText(displayedDate);
    }

    private void displayTime() {
        String displayedTime = this.birthDateHour + ":" + this.birthDateMinute;
        this.viewBindings.studenteditFragmentBirthTimeInputEt.setText(displayedTime);
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
        return new Student(name, id, phone, address, "", checkbox,
                this.birthDateYear, this.birthDateMonth, this.birthDateDay,
                this.birthDateHour, this.birthDateMinute);
    }
}