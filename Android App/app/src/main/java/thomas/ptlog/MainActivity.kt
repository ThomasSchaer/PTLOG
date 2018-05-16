package thomas.ptlog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var move: String = ""
    var kilogram: Int = 0
    var repetition: Int = 0
    val exerciseArray = ArrayList<Exercise>()
    lateinit var session: Intent
    lateinit var session2: Intent
    private val client = ExerciseApi()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.this_session -> {
                this.startActivity(session2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)

        session = Intent(this, Session::class.java)
        session2 = Intent(this, Session2::class.java)

        kilogramEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        kilogramEditText.setTextIsSelectable(true)

        repetitionEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        repetitionEditText.setTextIsSelectable(true)

        moveEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        moveEditText.setTextIsSelectable(true)

        getButton.setOnClickListener {
            loadExercises()
        }
        postButton.setOnClickListener {
            postExercise()
        }
        val inputConnectionMove = moveEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionKilograms = kilogramEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionRepetition = repetitionEditText.onCreateInputConnection(EditorInfo())

        keyboard.run {
            setInputConnection(inputConnectionMove)

            setKeyListener(object : KeyListener {
                override fun onClick(id: Int) {
                    val clickedView = findViewById<View>(id)

                    if (clickedView.tag != null) {
                        val myTag = clickedView.tag
                        if (myTag == "letter") {
                            keyboard.setInputConnection(inputConnectionMove)
                            moveEditText.requestFocus()
                            keyboard.commitText(clickedView)
                        }
                    }

                    val moveEmpty = moveEditText.text.isEmpty()
                    val kilogramEmpty = kilogramEditText.text.isEmpty()
                    val repetitionEmpty = repetitionEditText.text.isEmpty()

                    fun addExercise() {
                        if (moveEmpty || kilogramEmpty || repetitionEmpty) {
                            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG).show()
                        }
                        else {
                            move = moveEditText.text.toString()
                            kilogram = Integer.parseInt(kilogramEditText.text.toString())
                            repetition = Integer.parseInt(repetitionEditText.text.toString())
                            exerciseArray.add(Exercise(move, kilogram, repetition))
                            session.putExtra("addExercise", exerciseArray)
                            //CREATE request here
                            Toast.makeText(context, "Exercise logged", Toast.LENGTH_LONG).show()
                        }
                    }

                    if (id == R.id.button_next) {
                        if (moveEmpty) {
                            keyboard.setInputConnection(inputConnectionMove)
                            moveEditText.requestFocus()
                            return
                        }
                        if (kilogramEmpty) {
                            keyboard.setInputConnection(inputConnectionKilograms)
                            kilogramEditText.requestFocus()
                            return
                        }
                        if (repetitionEmpty) {
                            keyboard.setInputConnection(inputConnectionRepetition)
                            repetitionEditText.requestFocus()
                            return
                        }
                        addExercise()
                    }

                    if (id == R.id.button_set) {
                        addExercise()
                    }
                }
            })
        }

        moveEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                keyboard.setInputConnection(inputConnectionMove)
            }
        }

        kilogramEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                keyboard.setInputConnection(inputConnectionKilograms)
            }
        }

        repetitionEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                keyboard.setInputConnection(inputConnectionRepetition)
            }
        }
    }

    private fun postExercise() {
        val postExercises = client.postExercise(Exercise(editPost.text.toString(), 10, 2))
        postExercises.enqueue(object : Callback<Exercise> {
            override fun onFailure(call: Call<Exercise>?, t: Throwable?) {
                toast("FAIL")
            }

            override fun onResponse(call: Call<Exercise>, response: Response<Exercise>) {
                val exercise = response.body()
                editReturned.text = exercise?.move
                toast(exercise?.move.toString())
            }
        })

    }

    private fun loadExercises() {
        val exercisesCall = client.getExercises()
        exercisesCall.enqueue(object : Callback<List<Exercise>> {
            override fun onFailure(call: Call<List<Exercise>>?, t: Throwable?) {
                toast("FAIL")
            }

            override fun onResponse(call: Call<List<Exercise>>, response: Response<List<Exercise>>) {
                val exercises = response.body()
                toast(exercises?.joinToString { it.move }.toString())
            }
        })
    }
}

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
