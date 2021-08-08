package com.example.workouttrackerandroid

/*
    Data class to hold a single exercise
 */
data class ExerciseObject(
    var name: String,
    var numberOfReps: Int,
    var numberOfSets: Int,
    var weight: Double
){
}