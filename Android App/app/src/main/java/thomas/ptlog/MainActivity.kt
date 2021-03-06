package thomas.ptlog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thomas.ptlog.Companion.Companion.exerciseListName
import thomas.ptlog.Companion.Companion.extraName
import thomas.ptlog.Companion.Companion.tagLetterName

class MainActivity : AppCompatActivity() {

    var move: String = ""
    var weight: Int = 0
    var repetition: Int = 0
    var exercises = ArrayList<Exercise>()
    lateinit var session: Intent
    private val client = ExerciseApi()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.this_session -> {
                this.startActivityForResult(session, 1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        session = Intent(this, Session::class.java)

        weightEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        weightEditText.setTextIsSelectable(true)

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
        val inputConnectionWeight = weightEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionRepetition = repetitionEditText.onCreateInputConnection(EditorInfo())

        keyboard.run {
            setInputConnection(inputConnectionMove)
            setKeyListener(object : KeyListenerMain {
                override fun onClick(id: Int) {
                    val clickedView = findViewById<View>(id)

                    if (clickedView.tag != null) {
                        val myTag = clickedView.tag
                        if (myTag == tagLetterName()) {
                            keyboard.setInputConnection(inputConnectionMove)
                            moveEditText.requestFocus()
                            keyboard.commitText(clickedView)
                        }
                    }

                    val moveEmpty = moveEditText.text.isEmpty()
                    val weightEmpty = weightEditText.text.isEmpty()
                    val repetitionEmpty = repetitionEditText.text.isEmpty()

                    fun addExercise() {
                        if (moveEmpty || weightEmpty || repetitionEmpty) {
                            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            move = moveEditText.text.toString()
                            weight = Integer.parseInt(weightEditText.text.toString())
                            repetition = Integer.parseInt(repetitionEditText.text.toString())
                            exercises.add(Exercise(move, weight, repetition))
                            session.putExtra(extraName(), exercises)
                            Toast.makeText(context, "Exercise logged", Toast.LENGTH_LONG).show()
                        }
                    }

                    if (id == R.id.button_next) {
                        if (weightEmpty) {
                            keyboard.setInputConnection(inputConnectionWeight)
                            weightEditText.requestFocus()
                            return
                        }
                        if (repetitionEmpty) {
                            keyboard.setInputConnection(inputConnectionRepetition)
                            repetitionEditText.requestFocus()
                            return
                        }
                        if (weightEditText.hasFocus()) {

                            keyboard.setInputConnection(inputConnectionRepetition)
                            repetitionEditText.requestFocus()
                        }
                        else {
                            keyboard.setInputConnection(inputConnectionWeight)
                            weightEditText.requestFocus()
                        }
                    }

                    if (id == R.id.button_save) {
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

        weightEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                keyboard.setInputConnection(inputConnectionWeight)
            }
        }

        repetitionEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                keyboard.setInputConnection(inputConnectionRepetition)
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val exercisesExtra = data.getSerializableExtra(exerciseListName())
                if (exercisesExtra != null) {
                    exercises = exercisesExtra as ArrayList<Exercise>
                    session.putExtra(extraName(), exercises)
                }
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
