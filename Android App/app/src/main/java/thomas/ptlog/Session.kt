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
    private var uniqueMoveList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)
        session_mainList.adapter = MainListAdapter(this)
        exerciseList = intent.getSerializableExtra("addExercise") as java.util.ArrayList<Exercise>

        for (exercise in exerciseList) {
            if (!uniqueMoveList.contains(exercise.move)) {
                uniqueMoveList.add(exercise.move)
            }
        }
    }

    private inner class MainListAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context

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

            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row, viewGroup, false)
            val rowNameTextView = row.findViewById<TextView>(R.id.row_name)
            val rowStatTextView = row.findViewById<TextView>(R.id.row_stat)

            rowNameTextView.text = uniqueMoveList[position]
            for (exercise in exerciseList) {
                if (exercise.move == uniqueMoveList[position]) {
                    if (rowStatTextView.text == "Stats") {
                        rowStatTextView.text = getString(R.string.Stats, exercise.kilogram, exercise.repetition)
                    }
                    else {
                        rowStatTextView.text = rowStatTextView.text.toString() +
                                "\n" +
                                getString(R.string.Stats, exercise.kilogram, exercise.repetition)
                    }
                }
            }
            return row
        }
    }
}