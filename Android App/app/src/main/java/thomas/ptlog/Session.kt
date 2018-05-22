package thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.session2.*
import java.util.*

class Session : AppCompatActivity() {

    private var serializableExerciseList = ArrayList<Exercise>()
    private var uniqueMoveList = ArrayList<String>()
    private var parentList = ArrayList<ArrayList<Exercise>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session2)

        serializableExerciseList = intent.getSerializableExtra("addExercise") as java.util.ArrayList<Exercise>

        serializableExerciseList.forEach {
            if (!uniqueMoveList.contains(it.move)) {
                uniqueMoveList.add(it.move)
            }
        }

        uniqueMoveList.forEachIndexed { i, _ ->
            parentList.add(i, arrayListOf())
        }

        val childList = HashMap<String, ArrayList<Exercise>>()
        uniqueMoveList.forEachIndexed { i, move ->
            childList[move] = parentList[i]
        }

        serializableExerciseList.forEach {
            if (childList.containsKey(it.move)) {
                childList[it.move]?.add(it)
            }
        }

        val myAdapter = ExpandableListAdapter(uniqueMoveList, childList)
        exp_listView.setAdapter(myAdapter)
    }
}