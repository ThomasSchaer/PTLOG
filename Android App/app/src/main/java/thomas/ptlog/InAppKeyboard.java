
package thomas.ptlog;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class InAppKeyboard extends LinearLayout implements View.OnClickListener
{
    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private Boolean caps = false;
    private KeyListener keyListener;

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
        LayoutInflater.from(context).inflate(R.layout.keyboard_qwerty, this, true);
        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        button_1.setTag("number");
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        button_2.setTag("number");
        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        button_3.setTag("number");
        Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        button_4.setTag("number");
        Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        button_5.setTag("number");
        Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        button_6.setTag("number");
        Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        button_7.setTag("number");
        Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        button_8.setTag("number");
        Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        button_9.setTag("number");
        Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(this);
        button_0.setTag("number");

        Button button_q = findViewById(R.id.button_q);
        button_q.setOnClickListener(this);
        button_q.setTag("letter");
        Button button_w = findViewById(R.id.button_w);
        button_w.setOnClickListener(this);
        button_w.setTag("letter");
        Button button_e = findViewById(R.id.button_e);
        button_e.setOnClickListener(this);
        button_e.setTag("letter");
        Button button_r = findViewById(R.id.button_r);
        button_r.setOnClickListener(this);
        button_r.setTag("letter");
        Button button_t = findViewById(R.id.button_t);
        button_t.setOnClickListener(this);
        button_t.setTag("letter");
        Button button_y = findViewById(R.id.button_y);
        button_y.setOnClickListener(this);
        button_y.setTag("letter");
        Button button_u = findViewById(R.id.button_u);
        button_u.setOnClickListener(this);
        button_u.setTag("letter");
        Button button_i = findViewById(R.id.button_i);
        button_i.setOnClickListener(this);
        button_i.setTag("letter");
        Button button_o = findViewById(R.id.button_o);
        button_o.setOnClickListener(this);
        button_o.setTag("letter");
        Button button_p = findViewById(R.id.button_p);
        button_p.setOnClickListener(this);
        button_p.setTag("letter");

        Button button_a = findViewById(R.id.button_a);
        button_a.setOnClickListener(this);
        button_a.setTag("letter");
        Button button_s = findViewById(R.id.button_s);
        button_s.setOnClickListener(this);
        button_s.setTag("letter");
        Button button_d = findViewById(R.id.button_d);
        button_d.setOnClickListener(this);
        button_d.setTag("letter");
        Button button_f = findViewById(R.id.button_f);
        button_f.setOnClickListener(this);
        button_f.setTag("letter");
        Button button_g = findViewById(R.id.button_g);
        button_g.setOnClickListener(this);
        button_g.setTag("letter");
        Button button_h = findViewById(R.id.button_h);
        button_h.setOnClickListener(this);
        button_h.setTag("letter");
        Button button_j = findViewById(R.id.button_j);
        button_j.setOnClickListener(this);
        button_j.setTag("letter");
        Button button_k = findViewById(R.id.button_k);
        button_k.setOnClickListener(this);
        button_k.setTag("letter");
        Button button_l = findViewById(R.id.button_l);
        button_l.setOnClickListener(this);
        button_l.setTag("letter");

        Button button_z = findViewById(R.id.button_z);
        button_z.setOnClickListener(this);
        button_z.setTag("letter");
        Button button_x = findViewById(R.id.button_x);
        button_x.setOnClickListener(this);
        button_x.setTag("letter");
        Button button_c = findViewById(R.id.button_c);
        button_c.setOnClickListener(this);
        button_c.setTag("letter");
        Button button_v = findViewById(R.id.button_v);
        button_v.setOnClickListener(this);
        button_v.setTag("letter");
        Button button_b = findViewById(R.id.button_b);
        button_b.setOnClickListener(this);
        button_b.setTag("letter");
        Button button_n = findViewById(R.id.button_n);
        button_n.setOnClickListener(this);
        button_n.setTag("letter");
        Button button_m = findViewById(R.id.button_m);
        button_m.setOnClickListener(this);
        button_m.setTag("letter");

        Button button_dot = findViewById(R.id.button_dot);
        button_dot.setOnClickListener(this);
        button_dot.setTag("letter");
        ImageButton button_caps = findViewById(R.id.button_caps);
        button_caps.setOnClickListener(this);
        ImageButton button_backspace = findViewById(R.id.button_backspace);
        button_backspace.setOnClickListener(this);
        Button button_set = findViewById(R.id.button_set);
        button_set.setOnClickListener(this);
        Button button_space = findViewById(R.id.button_space);
        button_space.setOnClickListener(this);
        button_space.setTag("letter");
        Button button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(this);

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

        keyListener.onClick(view.getId());

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
        if (view.getId() == R.id.button_caps)
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
        if (view.getTag() == "number")
        {
            commitText(view);
        }
    }

    public void commitText(View view)
    {
        String value = keyValues.get(view.getId());
        inputConnection.commitText(value, 1);
    }

    public void setInputConnection(InputConnection ic)
    {
        inputConnection = ic;
    }

    public void setKeyListener(KeyListener keyListener)
    {
        this.keyListener = keyListener;
    }
}
