package thomas.ptlog

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.ImageButton
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.keyboard.view.*
import thomas.ptlog.Companion.Companion.tagLetterName
import thomas.ptlog.Companion.Companion.tagNumberName

class Keyboard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private val keyValues = SparseArray<String>()
    private var inputConnection: InputConnection? = null
    private var caps = false
    private var keyListenerMain: KeyListenerMain? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true)

        button_1.setOnClickListener(this)
        button_1.tag = tagNumberName()
        button_2.setOnClickListener(this)
        button_2.tag = tagNumberName()
        button_3.setOnClickListener(this)
        button_3.tag = tagNumberName()
        button_4.setOnClickListener(this)
        button_4.tag = tagNumberName()
        button_5.setOnClickListener(this)
        button_5.tag = tagNumberName()
        button_6.setOnClickListener(this)
        button_6.tag = tagNumberName()
        button_7.setOnClickListener(this)
        button_7.tag = tagNumberName()
        button_8.setOnClickListener(this)
        button_8.tag = tagNumberName()
        button_9.setOnClickListener(this)
        button_9.tag = tagNumberName()
        button_0.setOnClickListener(this)
        button_0.tag = tagNumberName()

        button_q.setOnClickListener(this)
        button_q.tag = tagLetterName()
        button_w.setOnClickListener(this)
        button_w.tag = tagLetterName()
        button_e.setOnClickListener(this)
        button_e.tag = tagLetterName()
        button_r.setOnClickListener(this)
        button_r.tag = tagLetterName()
        button_t.setOnClickListener(this)
        button_t.tag = tagLetterName()
        button_y.setOnClickListener(this)
        button_y.tag = tagLetterName()
        button_u.setOnClickListener(this)
        button_u.tag = tagLetterName()
        button_i.setOnClickListener(this)
        button_i.tag = tagLetterName()
        button_o.setOnClickListener(this)
        button_o.tag = tagLetterName()
        button_p.setOnClickListener(this)
        button_p.tag = tagLetterName()

        button_a.setOnClickListener(this)
        button_a.tag = tagLetterName()
        button_s.setOnClickListener(this)
        button_s.tag = tagLetterName()
        button_d.setOnClickListener(this)
        button_d.tag = tagLetterName()
        button_f.setOnClickListener(this)
        button_f.tag = tagLetterName()
        button_g.setOnClickListener(this)
        button_g.tag = tagLetterName()
        button_h.setOnClickListener(this)
        button_h.tag = tagLetterName()
        button_j.setOnClickListener(this)
        button_j.tag = tagLetterName()
        button_k.setOnClickListener(this)
        button_k.tag = tagLetterName()
        button_l.setOnClickListener(this)
        button_l.tag = tagLetterName()

        button_z.setOnClickListener(this)
        button_z.tag = tagLetterName()
        button_x.setOnClickListener(this)
        button_x.tag = tagLetterName()
        button_c.setOnClickListener(this)
        button_c.tag = tagLetterName()
        button_v.setOnClickListener(this)
        button_v.tag = tagLetterName()
        button_b.setOnClickListener(this)
        button_b.tag = tagLetterName()
        button_n.setOnClickListener(this)
        button_n.tag = tagLetterName()
        button_m.setOnClickListener(this)
        button_m.tag = tagLetterName()

        button_dot.setOnClickListener(this)
        button_dot.tag = tagLetterName()
        button_caps.setOnClickListener(this)
        button_backspace.setOnClickListener(this)
        button_save.setOnClickListener(this)
        button_space.setOnClickListener(this)
        button_space.tag = tagLetterName()
        button_next.setOnClickListener(this)

        keyValues.put(R.id.button_1, "1")
        keyValues.put(R.id.button_2, "2")
        keyValues.put(R.id.button_3, "3")
        keyValues.put(R.id.button_4, "4")
        keyValues.put(R.id.button_5, "5")
        keyValues.put(R.id.button_6, "6")
        keyValues.put(R.id.button_7, "7")
        keyValues.put(R.id.button_8, "8")
        keyValues.put(R.id.button_9, "9")
        keyValues.put(R.id.button_0, "0")

        keyValues.put(R.id.button_dot, ".")
        keyValues.put(R.id.button_space, " ")

        setKeyValues()
    }

    private fun setKeyValues() {
        if (caps) {
            keyValues.put(R.id.button_q, "Q")
            keyValues.put(R.id.button_w, "W")
            keyValues.put(R.id.button_e, "E")
            keyValues.put(R.id.button_r, "R")
            keyValues.put(R.id.button_t, "T")
            keyValues.put(R.id.button_y, "Y")
            keyValues.put(R.id.button_u, "U")
            keyValues.put(R.id.button_i, "I")
            keyValues.put(R.id.button_o, "O")
            keyValues.put(R.id.button_p, "P")

            keyValues.put(R.id.button_a, "A")
            keyValues.put(R.id.button_s, "S")
            keyValues.put(R.id.button_d, "D")
            keyValues.put(R.id.button_f, "F")
            keyValues.put(R.id.button_g, "G")
            keyValues.put(R.id.button_h, "H")
            keyValues.put(R.id.button_j, "J")
            keyValues.put(R.id.button_k, "K")
            keyValues.put(R.id.button_l, "L")

            keyValues.put(R.id.button_z, "Z")
            keyValues.put(R.id.button_x, "X")
            keyValues.put(R.id.button_c, "C")
            keyValues.put(R.id.button_v, "V")
            keyValues.put(R.id.button_b, "B")
            keyValues.put(R.id.button_n, "N")
            keyValues.put(R.id.button_m, "M")
        }
        else {
            keyValues.put(R.id.button_q, "q")
            keyValues.put(R.id.button_w, "w")
            keyValues.put(R.id.button_e, "e")
            keyValues.put(R.id.button_r, "r")
            keyValues.put(R.id.button_t, "t")
            keyValues.put(R.id.button_y, "y")
            keyValues.put(R.id.button_u, "u")
            keyValues.put(R.id.button_i, "i")
            keyValues.put(R.id.button_o, "o")
            keyValues.put(R.id.button_p, "p")

            keyValues.put(R.id.button_a, "a")
            keyValues.put(R.id.button_s, "s")
            keyValues.put(R.id.button_d, "d")
            keyValues.put(R.id.button_f, "f")
            keyValues.put(R.id.button_g, "g")
            keyValues.put(R.id.button_h, "h")
            keyValues.put(R.id.button_j, "j")
            keyValues.put(R.id.button_k, "k")
            keyValues.put(R.id.button_l, "l")

            keyValues.put(R.id.button_z, "z")
            keyValues.put(R.id.button_x, "x")
            keyValues.put(R.id.button_c, "c")
            keyValues.put(R.id.button_v, "v")
            keyValues.put(R.id.button_b, "b")
            keyValues.put(R.id.button_n, "n")
            keyValues.put(R.id.button_m, "m")
        }
    }

    override fun onClick(view: View) {
        if (inputConnection == null) return

        keyListenerMain!!.onClick(view.id)

        if (view.id == R.id.button_backspace) {
            val selectedText = inputConnection!!.getSelectedText(0)

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection!!.deleteSurroundingText(1, 0)
            }
            else {
                inputConnection!!.commitText("", 1)
            }
        }
        if (view.id == R.id.button_caps) {
            val capsButton = view as ImageButton
            caps = !caps

            if (caps) {
                capsButton.setImageResource(R.drawable.capslock_pressed_icon_simplified)
            }
            else {
                capsButton.setImageResource(R.drawable.capslock_icon)
            }
            setKeyValues()
        }
        if (view.tag === tagNumberName()) {
            commitText(view)
        }
    }

    fun commitText(view: View) {
        val value = keyValues.get(view.id)
        inputConnection!!.commitText(value, 1)
    }

    fun setInputConnection(ic: InputConnection) {
        inputConnection = ic
    }

    fun setKeyListener(keyListenerMain: KeyListenerMain) {
        this.keyListenerMain = keyListenerMain
    }
}