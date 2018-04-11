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

        moveEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        moveEditText.setTextIsSelectable(true)

        val inputConnectionKilograms = kilogramEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionRepetition = repetitionEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionMove = moveEditText.onCreateInputConnection(EditorInfo())

        keyboard.run {
            setInputConnection(inputConnectionMove)
            setKeyListener(object : KeyListener
            {
                override fun onClick(id: Int)
                {
                    if (id == R.id.button_next)
                    {
                        if (moveEditText.text.isEmpty())
                        {
                            keyboard.setInputConnection(inputConnectionMove)
                            moveEditText.requestFocus()
                            return
                        }
                        if (kilogramEditText.text.isEmpty())
                        {
                            keyboard.setInputConnection(inputConnectionKilograms)
                            kilogramEditText.requestFocus()
                            return
                        }
                        if (repetitionEditText.text.isEmpty())
                        {
                            keyboard.setInputConnection(inputConnectionRepetition)
                            repetitionEditText.requestFocus()
                            return
                        }
                        println("This println should be replaced with a method to add to database")
                    }
                    if (id == R.id.button_set)
                    {
                        println("This println should be replaced with a method to add to database")
                    }
                }
            })
        }

        kilogramEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
            {
                keyboard.setInputConnection(inputConnectionKilograms)
            }
        }

        repetitionEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
            {
                keyboard.setInputConnection(inputConnectionRepetition)
            }
        }
    }
}

