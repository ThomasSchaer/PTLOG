package thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.session.*
import java.util.*

class Session : AppCompatActivity() {

    private var serializableExerciseList = ArrayList<Exercise>()
    private var uniqueMoveList = ArrayList<String>()
    private var parentList = ArrayList<ArrayList<Exercise>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)

        serializableExerciseList = intent.getSerializableExtra("addExercise") as ArrayList<Exercise>

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

        val expandableListAdapter = ExpandableListAdapter(uniqueMoveList, childList)
        expandableListView.setAdapter(expandableListAdapter)
    }
}