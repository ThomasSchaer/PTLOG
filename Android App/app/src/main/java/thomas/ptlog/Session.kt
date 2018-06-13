package thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.session.*
import thomas.ptlog.Companion.Companion.exerciseListName
import thomas.ptlog.Companion.Companion.extraName
import java.util.*

class Session : AppCompatActivity() {

    private var exerciseList = ArrayList<Exercise>()
    private var uniqueMoveList = ArrayList<String>()
    private var parentList = ArrayList<ArrayList<Exercise>>()
    private var keyListenerSession: KeyListenerSession? = null
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session)

        val exercisesExtra = intent.getSerializableExtra(extraName())

        if (exercisesExtra != null) {
            exerciseList = exercisesExtra as ArrayList<Exercise>
        }

        exerciseList.forEach {
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

        exerciseList.forEach {
            if (childList.containsKey(it.move)) {
                childList[it.move]?.add(it)
            }
        }

        keyListenerSession = object : KeyListenerSession {
            override fun deleteExercise(move: String, weight: Int, repetition: Int) {
                val mutableIterator = exerciseList.iterator()
                for (it in mutableIterator) {
                    if (it.move == move && it.weight == weight && it.repetition == repetition) {
                        mutableIterator.remove()
                        break
                    }
                }
                intent.putExtra(exerciseListName(), exerciseList)
                clicked = true
                setResult(RESULT_OK, intent)
                finish()
            }
        }
        val expandableListAdapter = ExpandableListAdapter(uniqueMoveList, childList, keyListenerSession)
        expandableListView.setAdapter(expandableListAdapter)
    }

    override fun onBackPressed() {
        if (!clicked) {
            setResult(RESULT_CANCELED, intent)
            super.onBackPressed()
        }
        finish()
    }
}