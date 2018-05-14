package thomas.ptlog

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ExerciseClient {
    @GET("/")
    fun getExercise(): Call<Exercise>

    @POST("/exercises")
    fun createExercise(): Call<Exercise>
}