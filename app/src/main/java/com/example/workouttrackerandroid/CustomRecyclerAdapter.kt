package com.example.workouttrackerandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class CustomRecyclerAdapter(var exercises: MutableList<ExerciseObject>,
                            var listener: EditButtonClick):
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){


        /*
            Wrapper for a view that the recycler will display
         */
        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            var nameView: TextView = view.findViewById(R.id.nameTextView);
            var repsView: TextView = view.findViewById(R.id.repsTextView);
            var setsView: TextView = view.findViewById(R.id.setsTextView);
            var weightView: TextView = view.findViewById(R.id.weightTextView);
            var editButton: Button = view.findViewById(R.id.editButton);
            var deleteButton: Button = view.findViewById(R.id.deleteButton);
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

        holder.editButton.setOnClickListener {
            listener.onEditClick(it, holder, position);
            this.notifyItemChanged(position);
        };

        holder.deleteButton.setOnClickListener {
            exercises.removeAt(position);
            this.notifyItemRemoved(position);
        }
    }

    override fun getItemCount(): Int {
        return exercises.size;
    }
}