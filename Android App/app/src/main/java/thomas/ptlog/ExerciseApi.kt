package thomas.ptlog

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExerciseApi {

    private val exerciseApi: ExerciseClient

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ptlog-mongo.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        exerciseApi = retrofit.create(ExerciseClient::class.java)
    }

    fun getExercises(): Call<List<Exercise>> {
        return exerciseApi.getExercises()
    }

    fun postExercise(exercise: Exercise): Call<Exercise>{
        return exerciseApi.postExercise(exercise)
    }
}