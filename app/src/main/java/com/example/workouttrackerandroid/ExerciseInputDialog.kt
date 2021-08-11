package com.example.workouttrackerandroid

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import kotlin.ClassCastException

class ExerciseInputDialog: DialogFragment() {
    lateinit var listener: ExerciseInputDialogListener;

    interface ExerciseInputDialogListener{
        fun onDialogPosClick(dialog: DialogFragment, view: View)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context);

        try {
            listener = context as ExerciseInputDialogListener;
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + " must implement ExerciseInputDialogListener");
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val build = AlertDialog.Builder(it);
            val inflater = requireActivity().layoutInflater.inflate(R.layout.exerciseinput, null);

            build.setView(inflater);

            build.setMessage("Input a New Exercise");

            build.setPositiveButton("Add",
                DialogInterface.OnClickListener { _, _ ->
                    listener.onDialogPosClick(this, inflater);
                });

            return build.create();
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}