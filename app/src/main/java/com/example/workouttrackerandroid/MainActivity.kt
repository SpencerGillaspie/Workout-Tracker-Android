package com.example.workouttrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),
                    ExerciseInputDialog.ExerciseInputDialogListener,
                    EditButtonClick{

    var exercises = mutableListOf<ExerciseObject>();
    var adapter = CustomRecyclerAdapter(exercises, this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the RecyclerViewAdapter to the UI
        var recyclerView = findViewById<RecyclerView>(R.id.exercisesRV);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter;

        val addButton: Button = findViewById(R.id.addButton);

        // When button is clicked, add whatever is entered to the list
        addButton.setOnClickListener {
            ShowDialog("add");
        };
    }

    /*
        parameters: The DialogFragment this is being called from and the View of the DialogFragment
        returns: None

        description: This function implements the onButtonClick function from the ExerciseInputDialog.
                        This is so that the data inputted in the Dialog can be 'returned' to the main
                        activity.
     */
    override fun onDialogPosClick(dialog: DialogFragment, view: View) {
        if (dialog.tag == "add"){
            AddExercise(exercises, view);
        } else {
            EditExercise(exercises, view, dialog.tag!!.toInt());
        }
    }

    /*
        parameters: View of ExerciseObject being edited, the ViewHolder of the ExerciseObject being
                        edited, and the int position of the ExerciseObject being edited
        returns: None

        description: This MainActivity extends the EditButtonClick interface. This is where the
                        implementation of the function called when the "Edit" button is clicked for an
                        exercise. This is needed since you can't show a dialog from a class extending
                        RecyclerView.Adapter.
     */
    override fun onEditClick(
        it: View,
        holder: CustomRecyclerAdapter.ViewHolder,
        position: Int
    ){
        ShowDialog(position.toString());
    }

    /*
        parameters: MutableList of ExerciseObjects, the edited view, and the int position
                        of the exercise to be edited
        returns: None
     */
    private fun EditExercise(list: MutableList<ExerciseObject>, view: View, position: Int){
        list[position].name = view.findViewById<EditText>(R.id.nameEditText).text.toString();
        list[position].numberOfReps = view.findViewById<EditText>(R.id.repsEditText).text.toString().toInt();
        list[position].numberOfSets = view.findViewById<EditText>(R.id.setsEditText).text.toString().toInt();
        list[position].weight = view.findViewById<EditText>(R.id.weightEditText).text.toString().toDouble();

        adapter.notifyItemChanged(position);
    }

    /*
        parameters: MutableList of ExerciseObjects
        returns: None
     */
    private fun AddExercise(list: MutableList<ExerciseObject>, view: View) {
        list.add (ExerciseObject(view.findViewById<EditText>(R.id.nameEditText).text.toString(),
            view.findViewById<EditText>(R.id.repsEditText).text.toString().toInt(),
            view.findViewById<EditText>(R.id.setsEditText).text.toString().toInt(),
            view.findViewById<EditText>(R.id.weightEditText).text.toString().toDouble()));

        adapter.notifyItemInserted((list.size - 1));
    }

    /*
        parameter: String to be used as the tag when showing the Dialog Fragment
        returns: None
     */
    private fun ShowDialog(tag: String){
        val fragment = ExerciseInputDialog();

        fragment.show(supportFragmentManager, tag);
    }
}