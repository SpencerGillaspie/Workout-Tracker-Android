package com.example.workouttrackerandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomRecyclerAdapter(var exercises: MutableList<ExerciseObject>):
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){


        /*
            Wrapper for a view that the recycler will display
         */
        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            lateinit var nameView: TextView;
            lateinit var repsView: TextView;
            lateinit var setsView: TextView;
            lateinit var weightView: TextView;

            init {
                nameView = view.findViewById(R.id.nameTextView);
                repsView = view.findViewById(R.id.repsTextView);
                setsView = view.findViewById(R.id.setsTextView);
                weightView = view.findViewById(R.id.weightTextView);
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Get the exercise view
        val exerciseView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise, parent, false);

        // Create and return the ViewHolder with the exerciseView
        return ViewHolder(exerciseView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = exercises[position].name;
        holder.repsView.text = exercises[position].numberOfReps.toString();
        holder.setsView.text = exercises[position].numberOfSets.toString();
        holder.weightView.text = exercises[position].weight.toString();
    }

    override fun getItemCount(): Int {
        return exercises.size;
    }
}