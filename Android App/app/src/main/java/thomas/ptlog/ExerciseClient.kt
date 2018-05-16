package thomas.ptlog

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExerciseClient {
    @GET("/exercises")
    fun getExercises(): Call<List<Exercise>>

    @POST("/exercises")
    fun postExercise(@Body exercise: Exercise): Call<Exercise>
}