package com.thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    override fun onCreate(saveInstanceState: Bundle?)
    {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)

        kilogramEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        kilogramEditText.setTextIsSelectable(true)

        repetitionEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        repetitionEditText.setTextIsSelectable(true)

        val inputConnectionKilograms = kilogramEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionRepetition = repetitionEditText.onCreateInputConnection(EditorInfo())

        keyboard.run {
            setInputConnection(inputConnectionKilograms)
            setKeyListener(object : KeyListener
            {
                override fun onClick(id: Int)
                {
                    if (id == R.id.button_next)
                    {
                        keyboard.setInputConnection(inputConnectionRepetition)
                    }
                }
            })
        }

        kilogramEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
            {
                keyboard.setInputConnection(inputConnectionKilograms)
            }
        }

        repetitionEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
            {
                keyboard.setInputConnection(inputConnectionRepetition)
            }
        }
    }
}

