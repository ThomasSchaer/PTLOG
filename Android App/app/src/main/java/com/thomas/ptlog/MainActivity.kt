package com.thomas.ptlog

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.widget.SearchViewCompat.setInputType
import android.text.Editable
import android.text.Selection
import android.text.method.Touch.onTouchEvent
import android.widget.EditText
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.View.OnFocusChangeListener
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)


        KG_editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        KG_editText.setTextIsSelectable(true)

        //REP_editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        //REP_editText.setTextIsSelectable(true)
        KG_editText.setText("thomas")
        text1.setText("foo")

        val inputConnectionForKG = KG_editText.onCreateInputConnection(EditorInfo())

        keyboard.snot = this
        text2.setText("bar")

        //val inputConnectionForREP = REP_editText.onCreateInputConnection(EditorInfo())


        keyboard.setInputConnection(inputConnectionForKG)

        text1.setText("ios")
        text1.setSelection(0);

        val button = findViewById<View>(android.R.id.button1) as Button


        button.setOnClickListener {
            //Selection.setSelection(REP_editText.editableText, 0)
            REP_editText.requestFocus()
        }


        //keyboard.setInputConnection(inputConnectionForREP)


            //val inputConnectionForREP = REP_editText.onCreateInputConnection(EditorInfo())
            //keyboard.setInputConnection(inputConnectionForREP)
            /*
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("http://localhost:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val exerciseService: ExerciseService = retrofit.create(ExerciseService::class.java)
            val exercisesContainer = exerciseService.listExercises()
            exercisesContainer.enqueue(object : Callback<ExerciseContainer> {
                override fun onResponse(call: Call<ExerciseContainer>, response: Response<ExerciseContainer>) {
                    val myItem = response.body()
                }

                override fun onFailure(call: Call<ExerciseContainer>, t: Throwable) {
                    //Handle failure
                }
            })
            */


    }
    /*
    @SuppressLint("ClickableViewAccessibility")
    fun registerEditText(resid: Int) {
        // Find the EditText 'resid'
        val edittext = findViewById<View>(resid) as EditText
        edittext.inputType

        // Disable standard keyboard hard way
        edittext.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val edittext = v as EditText
                val inType = edittext.inputType       // Backup the input type
                edittext.inputType = InputType.TYPE_NULL // Disable standard keyboard
                edittext.onTouchEvent(event)               // Call native handler
                edittext.inputType = inType              // Restore input type
                return true // Consume touch event
            }
        })
        // Disable spell check (hex strings look like words to Android)
        edittext.inputType = edittext.inputType or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }
    */
}
