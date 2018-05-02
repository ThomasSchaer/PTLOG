package thomas.ptlog

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

class MainActivity : AppCompatActivity() {

    var move: String = ""
    var kilogram: Int = 0
    var repetition: Int = 0
    val exerciseArray = ArrayList<Exercise>()
    lateinit var session: Intent

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.this_session -> {
                this.startActivity(session)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        session = Intent(this, Session::class.java)

        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)

        kilogramEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        kilogramEditText.setTextIsSelectable(true)

        repetitionEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        repetitionEditText.setTextIsSelectable(true)

        moveEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        moveEditText.setTextIsSelectable(true)

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
}

