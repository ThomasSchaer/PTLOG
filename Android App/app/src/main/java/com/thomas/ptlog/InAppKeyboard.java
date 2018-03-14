
package com.thomas.ptlog;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;

public class InAppKeyboard extends LinearLayout implements View.OnClickListener
{

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public InAppKeyboard(Context context)
    {
        this(context, null, 0);
    }

    public InAppKeyboard(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public InAppKeyboard(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        Button buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);

        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_0, "0");
        keyValues.put(R.id.button_enter, "\n");

    }

    @Override
    public void onClick(View view)
    {
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.button_delete)
        {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText))
            {
                inputConnection.deleteSurroundingText(1, 0);
            }
            else
            {
                inputConnection.commitText("", 1);
            }
        }
        else
        {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic)
    {
        inputConnection = ic;
    }


}
