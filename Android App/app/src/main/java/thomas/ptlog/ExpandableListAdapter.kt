package thomas.ptlog

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import kotlinx.android.synthetic.main.child_layout.view.*
import kotlinx.android.synthetic.main.parent_layout.view.*
import java.util.*

class ExpandableListAdapter(private val header_titles: ArrayList<String>, private val child_titles: HashMap<String, ArrayList<Exercise>>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return header_titles.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return child_titles[header_titles[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return header_titles[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return child_titles[header_titles[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpandable: Boolean, convertView: View?, parent: ViewGroup): View {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.parent_layout, parent, false)
        val title = getGroup(groupPosition) as String
        val expandableListView = parent as ExpandableListView
        expandableListView.expandGroup(groupPosition)

        row.heading_item.setTypeface(null, Typeface.BOLD)
        row.heading_item.text = title

        return row
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.child_layout, parent, false)
        val exercise = getChild(groupPosition, childPosition) as Exercise
        val title = "${exercise.kilogram} KG x ${exercise.repetition} reps"

        row.child_item.setTypeface(null, Typeface.BOLD)
        row.child_item.text = title

        return row
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
