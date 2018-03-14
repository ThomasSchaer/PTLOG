package com.example.thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.inputmethod.InputConnection
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EditText edittext = (EditText) find

        EditText edittext = (EditText) findViewById(R.id.editText)
        InAppKeyboard inappkeyboard = (InAppKeyboard) findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        editText.setTextIsSelectable(true)

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo())
        keyboard.setInputConnection(ic);
        

    }
}
