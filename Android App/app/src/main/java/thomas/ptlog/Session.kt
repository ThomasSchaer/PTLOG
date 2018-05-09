package thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.row.view.*
import kotlinx.android.synthetic.main.session.*

class Session : AppCompatActivity() {

    private var exerciseList = ArrayList<Exercise>()
    private var uniqueMoveList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)
        session_mainList.adapter = MainListAdapter()
        exerciseList = intent.getSerializableExtra("addExercise") as java.util.ArrayList<Exercise>

        for (exercise in exerciseList) {
            if (!uniqueMoveList.contains(exercise.move)) {
                uniqueMoveList.add(exercise.move)
            }
        }
    }

    private inner class MainListAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return uniqueMoveList.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val row: View
            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)
                row = layoutInflater.inflate(R.layout.row, viewGroup, false)
                val viewHolder = ViewHolder(row.name_textView, row.stats_textView)
                row.tag = viewHolder
            }
            else {
                row = convertView
            }

            val viewHolder = row.tag as ViewHolder

            var stats = ""
            for (exercise in exerciseList) {
                if (exercise.move == uniqueMoveList[position]) {
                    stats = stats + getString(R.string.Stats, exercise.kilogram, exercise.repetition) + "\n"
                }
            }
            viewHolder.name_textView.text = uniqueMoveList[position]
            viewHolder.stats_textView.text = stats.substring(0, stats.length - 1)

            return row
        }

        inner class ViewHolder(val name_textView: TextView, val stats_textView: TextView)
    }
}