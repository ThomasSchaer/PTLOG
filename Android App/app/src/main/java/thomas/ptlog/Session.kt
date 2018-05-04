package thomas.ptlog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.session.*

class Session : AppCompatActivity() {

    private var exerciseList = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)
        main_listview.adapter = MyCustomAdapter(this)
        exerciseList = intent.getSerializableExtra("addExercise") as java.util.ArrayList<Exercise>
    }

    private inner class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context

        override fun getCount(): Int {
            return exerciseList.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)

            val rowMain = layoutInflater.inflate(R.layout.session_row, viewGroup, false)
            val nameTextView = rowMain.findViewById<TextView>(R.id.name_textView)
            val statTextView = rowMain.findViewById<TextView>(R.id.stat_textView)
            nameTextView.text = exerciseList[position].move
            statTextView.text = getString(R.string.Stats, exerciseList[position].kilogram, exerciseList[position].repetition)

            return rowMain

        }
    }
}
