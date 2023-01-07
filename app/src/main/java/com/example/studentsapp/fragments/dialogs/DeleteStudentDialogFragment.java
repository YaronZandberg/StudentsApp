package com.example.studentsapp.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DeleteStudentDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete student");
        builder.setMessage("Deleting student operation was completed successfully");
        builder.setPositiveButton("OK", (dialogInterface, i) ->
                Toast.makeText(getContext(),"Student has been deleted",Toast.LENGTH_LONG).show());
        return builder.create();
    }
}
