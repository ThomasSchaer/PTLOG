package com.thomas.ptlog

import retrofit2.Call
import retrofit2.http.GET

interface ExerciseService {
    @GET("/exercises")
    fun listExercises(): Call<ExerciseContainer>
}