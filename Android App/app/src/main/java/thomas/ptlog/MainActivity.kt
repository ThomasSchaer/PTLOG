package thomas.ptlog

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            R.id.this_session ->
            {
                val myIntent = Intent(this, Session::class.java)
                this.startActivity(myIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

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

        val inputConnectionMove = moveEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionKilograms = kilogramEditText.onCreateInputConnection(EditorInfo())
        val inputConnectionRepetition = repetitionEditText.onCreateInputConnection(EditorInfo())

        keyboard.run {
            setInputConnection(inputConnectionMove)
            setKeyListener(object : KeyListener
            {
                override fun onClick(id: Int)
                {
                    val clickedView = findViewById<View>(id)

                    if (clickedView.tag != null)
                    {
                        val myTag = clickedView.tag
                        if (myTag == "letter")
                        {
                            keyboard.setInputConnection(inputConnectionMove)
                            moveEditText.requestFocus()
                            keyboard.commitText(clickedView)
                        }
                    }

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

        moveEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
            {
                keyboard.setInputConnection(inputConnectionMove)
            }
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

