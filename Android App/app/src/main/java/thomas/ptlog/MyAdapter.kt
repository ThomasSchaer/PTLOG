package thomas.ptlog

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import kotlinx.android.synthetic.main.child_layout.view.*
import kotlinx.android.synthetic.main.parent_layout.view.*
import java.util.*

class MyAdapter(private val header_titles: List<String>, private val child_titles: HashMap<String, List<String>>) : BaseExpandableListAdapter() {

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
        val row: View
        val title = getGroup(groupPosition) as String
        val layoutInflater = LayoutInflater.from(parent.context)
        row = layoutInflater.inflate(R.layout.parent_layout, parent, false)

        row.heading_item.setTypeface(null, Typeface.BOLD)
        row.heading_item.text = title

        return row
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val row: View
        val title = getChild(groupPosition, childPosition) as String

        val layoutInflater = LayoutInflater.from(parent.context)
        row = layoutInflater.inflate(R.layout.child_layout, parent, false)

        row.child_item.setTypeface(null, Typeface.BOLD)
        row.child_item.text = title

        return row
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
