
package com.thomas.ptlog;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.Toast;


public class InAppKeyboard extends LinearLayout implements View.OnClickListener
{
    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private Boolean caps = false;

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
        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(this);

        Button button_q = findViewById(R.id.button_q);
        button_q.setOnClickListener(this);
        Button button_w = findViewById(R.id.button_w);
        button_w.setOnClickListener(this);
        Button button_e = findViewById(R.id.button_e);
        button_e.setOnClickListener(this);
        Button button_r = findViewById(R.id.button_r);
        button_r.setOnClickListener(this);
        Button button_t = findViewById(R.id.button_t);
        button_t.setOnClickListener(this);
        Button button_y = findViewById(R.id.button_y);
        button_y.setOnClickListener(this);
        Button button_u = findViewById(R.id.button_u);
        button_u.setOnClickListener(this);
        Button button_i = findViewById(R.id.button_i);
        button_i.setOnClickListener(this);
        Button button_o = findViewById(R.id.button_o);
        button_o.setOnClickListener(this);
        Button button_p = findViewById(R.id.button_p);
        button_p.setOnClickListener(this);

        Button button_a = findViewById(R.id.button_a);
        button_a.setOnClickListener(this);
        Button button_s = findViewById(R.id.button_s);
        button_s.setOnClickListener(this);
        Button button_d = findViewById(R.id.button_d);
        button_d.setOnClickListener(this);
        Button button_f = findViewById(R.id.button_f);
        button_f.setOnClickListener(this);
        Button button_g = findViewById(R.id.button_g);
        button_g.setOnClickListener(this);
        Button button_h = findViewById(R.id.button_h);
        button_h.setOnClickListener(this);
        Button button_j = findViewById(R.id.button_j);
        button_j.setOnClickListener(this);
        Button button_k = findViewById(R.id.button_k);
        button_k.setOnClickListener(this);
        Button button_l = findViewById(R.id.button_l);
        button_l.setOnClickListener(this);

        Button button_z = findViewById(R.id.button_z);
        button_z.setOnClickListener(this);
        Button button_x = findViewById(R.id.button_x);
        button_x.setOnClickListener(this);
        Button button_c = findViewById(R.id.button_c);
        button_c.setOnClickListener(this);
        Button button_v = findViewById(R.id.button_v);
        button_v.setOnClickListener(this);
        Button button_b = findViewById(R.id.button_b);
        button_b.setOnClickListener(this);
        Button button_n = findViewById(R.id.button_n);
        button_n.setOnClickListener(this);
        Button button_m = findViewById(R.id.button_m);
        button_m.setOnClickListener(this);

        Button buttonEnter = findViewById(R.id.button_dot);
        buttonEnter.setOnClickListener(this);
        ImageButton buttonCaps = findViewById(R.id.button_caps);
        buttonCaps.setOnClickListener(this);
        ImageButton buttonBackspace = findViewById(R.id.button_backspace);
        buttonBackspace.setOnClickListener(this);
        Button buttonSet = findViewById(R.id.button_set);
        buttonSet.setOnClickListener(this);
        Button buttonSpace = findViewById(R.id.button_space);
        buttonSpace.setOnClickListener(this);
        Button buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(this);

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

        keyValues.put(R.id.button_dot, ".");
        keyValues.put(R.id.button_space, " ");

        setKeyValues();
    }

    public void setKeyValues()
    {
        if (caps)
        {
            keyValues.put(R.id.button_q, "Q");
            keyValues.put(R.id.button_w, "W");
            keyValues.put(R.id.button_e, "E");
            keyValues.put(R.id.button_r, "R");
            keyValues.put(R.id.button_t, "T");
            keyValues.put(R.id.button_y, "Y");
            keyValues.put(R.id.button_u, "U");
            keyValues.put(R.id.button_i, "I");
            keyValues.put(R.id.button_o, "O");
            keyValues.put(R.id.button_p, "P");

            keyValues.put(R.id.button_a, "A");
            keyValues.put(R.id.button_s, "S");
            keyValues.put(R.id.button_d, "D");
            keyValues.put(R.id.button_f, "F");
            keyValues.put(R.id.button_g, "G");
            keyValues.put(R.id.button_h, "H");
            keyValues.put(R.id.button_j, "J");
            keyValues.put(R.id.button_k, "K");
            keyValues.put(R.id.button_l, "L");

            keyValues.put(R.id.button_z, "Z");
            keyValues.put(R.id.button_x, "X");
            keyValues.put(R.id.button_c, "C");
            keyValues.put(R.id.button_v, "V");
            keyValues.put(R.id.button_b, "B");
            keyValues.put(R.id.button_n, "N");
            keyValues.put(R.id.button_m, "M");
        }
        else
        {
            keyValues.put(R.id.button_q, "q");
            keyValues.put(R.id.button_w, "w");
            keyValues.put(R.id.button_e, "e");
            keyValues.put(R.id.button_r, "r");
            keyValues.put(R.id.button_t, "t");
            keyValues.put(R.id.button_y, "y");
            keyValues.put(R.id.button_u, "u");
            keyValues.put(R.id.button_i, "i");
            keyValues.put(R.id.button_o, "o");
            keyValues.put(R.id.button_p, "p");

            keyValues.put(R.id.button_a, "a");
            keyValues.put(R.id.button_s, "s");
            keyValues.put(R.id.button_d, "d");
            keyValues.put(R.id.button_f, "f");
            keyValues.put(R.id.button_g, "g");
            keyValues.put(R.id.button_h, "h");
            keyValues.put(R.id.button_j, "j");
            keyValues.put(R.id.button_k, "k");
            keyValues.put(R.id.button_l, "l");

            keyValues.put(R.id.button_z, "z");
            keyValues.put(R.id.button_x, "x");
            keyValues.put(R.id.button_c, "c");
            keyValues.put(R.id.button_v, "v");
            keyValues.put(R.id.button_b, "b");
            keyValues.put(R.id.button_n, "n");
            keyValues.put(R.id.button_m, "m");
        }
    }

    @Override
    public void onClick(View view)
    {

        if (inputConnection == null)
            return;

/*
        if (view.getId() == R.id.button_backspace)
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

        else*/ if (view.getId() == R.id.button_caps)
        {
            ImageButton capsButton = (ImageButton) view;
            caps = !caps;

            if (caps)
            {
                capsButton.setImageResource(R.drawable.caps_icon_pressed);
            }
            else
            {
                capsButton.setImageResource(R.drawable.caps_icon);

            }
            setKeyValues();

        }
        else if (view.getId() == R.id.button_next)
        {
            System.out.println("next button");

            LayoutInflater li = LayoutInflater.from(getContext()); // 1
            View v = li.inflate(R.layout.activity_main,null, false);

            EditText KG_editText = v.findViewById(R.id.KG_editText);
            KG_editText.setText("Schaer");
            System.out.println("->"+KG_editText.getText()+"<-");
            /*
            View.inflate();


            LayoutInflater inflater = LayoutInflater.from(getActi.this); // 1

            View theInflatedView = inflater.inflate(R.layout.activity_main, null); // 2 and 3

            View v = inflate(R.id.)
            */

            /*
            final InputConnection inputConnectionForREP = REP_editText.onCreateInputConnection(new EditorInfo());
            REP_editText.requestFocus();

            REP_editText.setOnFocusChangeListener(new OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View view, boolean hasFocus)
                {
                    if (hasFocus)
                    {



                        setInputConnection(inputConnectionForREP);
                    }
                }
            });
            */



            //requestFocusFromTouch();
            //REP_editText.setSelection(0);

            //Selection.setSelection((Editable) REP_editText.getText(),0);


            //InputConnection inputConnectionForREP = REP_editText.onCreateInputConnection(new EditorInfo());


            //setInputConnection(inputConnectionForREP);

            //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);




            //InputConnection ic = REP_editText.inputonCreateInputConnection(new EditorInfo());
            //setInputConnection(ic);


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
