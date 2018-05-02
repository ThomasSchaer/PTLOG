package thomas.ptlog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.session.*

class Session : AppCompatActivity() {

    private val exerciseArray = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)

        main_listview.adapter = MyCustomAdapter(this)

        button2.setOnClickListener {

            val exerciseList = intent.getSerializableExtra("addExercise") as java.util.ArrayList<Exercise>
            exerciseList.size

            for (i in 0 until exerciseList.size) {
                val move = exerciseList[i].move
                val kilogram = exerciseList[i].kilogram
                val repetition = exerciseList[i].repetition
                exerciseArray.add(Exercise(move, kilogram, repetition))
            }

            for (i in 0 until exerciseArray.size) {
                println(exerciseArray[i].move)
                println(exerciseArray[i].kilogram)
                println(exerciseArray[i].repetition)
            }
        }
    }

    private class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context

        override fun getCount(): Int {
            return 5
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "HEJ THOMAS"
            return textView
        }
    }
}
