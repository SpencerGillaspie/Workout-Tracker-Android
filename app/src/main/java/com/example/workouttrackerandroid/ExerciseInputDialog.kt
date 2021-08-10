package com.example.workouttrackerandroid

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

class ExerciseInputDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val build = AlertDialog.Builder(it);

            val inflater = requireActivity().layoutInflater;

            build.setView(inflater.inflate(R.layout.exerciseinput, null))

            build.setMessage("Input a New Exercise");

            return build.create();
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}