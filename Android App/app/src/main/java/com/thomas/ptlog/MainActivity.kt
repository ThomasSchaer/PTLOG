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

        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        editText.setTextIsSelectable(true)

        val ic = editText.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnection(ic)
    }
}
