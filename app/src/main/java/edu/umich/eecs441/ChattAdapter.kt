package edu.umich.eecs441

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.chatt_item.view.*

class ChattAdapter(context: Context, users: ArrayList<Chatt?>?) :
    ArrayAdapter<Chatt?>(context, 0, users!!) {

    override fun getView(position: Int, chattView: View?, parent: ViewGroup): View {
        var convertView: View? = chattView
        val chatt = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chatt_item, parent, false)
        }

        convertView!!.usernameTextView.text = chatt!!.username
        convertView.messageTextView.text = chatt.message
        convertView.timestampTextView.text = chatt.timestamp

        return convertView
    }
}