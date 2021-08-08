package com.example.workouttrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var exercises = mutableListOf<ExerciseObject>();

        for (i in 1..5){
            exercises.add(ExerciseObject("What", i, i, i.toDouble()));
        }

        var adapter = CustomRecyclerAdapter(exercises);

        var recyclerView = findViewById<RecyclerView>(R.id.exercisesRV);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter;
    }

}