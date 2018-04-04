package com.thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)

        kilogramEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        kilogramEditText.setTextIsSelectable(true)

        val inputConnectionForKG = kilogramEditText.onCreateInputConnection(EditorInfo())

        keyboard.setKeyListener(object : KeyListener {
            override fun onClick(id: Int) {
                if (id == R.id.button_next) {
                    kilogramEditText.setText("sebastian")
                }
            }
        })
        keyboard.setInputConnection(inputConnectionForKG)
    }
}

