package com.example.workouttrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var exercises = mutableListOf<ExerciseObject>();

        var adapter = CustomRecyclerAdapter(exercises);

        // Attach the RecyclerViewAdapter to the UI
        var recyclerView = findViewById<RecyclerView>(R.id.exercisesRV);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter;

        val button: Button = findViewById(R.id.addButton);

        // When button is clicked, add whatever is entered to the list
        button.setOnClickListener {
            ShowDialog();
            //AddExercise(exercises);
            //adapter.notifyItemChanged(exercises.size - 1);
        }
    }

    /*
        parameters: MutableList of ExerciseObjects
        returns: None
     */
    private fun AddExercise(list: MutableList<ExerciseObject>) {
        val nameText = findViewById<EditText>(R.id.nameEditText).text;
        val repsText = findViewById<EditText>(R.id.repsEditText).text;
        val setsText = findViewById<EditText>(R.id.setsEditText).text;
        val weightText = findViewById<EditText>(R.id.weightEditText).text;

        list.add(
            ExerciseObject(
                nameText.toString(), repsText.toString().toInt(),
                setsText.toString().toInt(), weightText.toString().toDouble()
            )
        )
    }

    private fun ShowDialog(){
        val fragment = ExerciseInputDialog();

        fragment.show(supportFragmentManager, "input");
    }
}