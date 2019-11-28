package com.example.cultivosbiologicos

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinAdapter(ctx : Context, textViewResourceId : Int, val values : List<Solution>)
        : ArrayAdapter<Solution>(ctx, textViewResourceId, values) {

    override fun getCount(): Int = values.size

    override fun getItem(position: Int): Solution? = values[position]

    override fun getItemId(position: Int) : Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)

        label.text = values[position].solution

        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val label = super.getView(position, convertView, parent) as TextView

        label.text = values[position].solution

        return label
    }

    override fun getPosition(item: Solution?) : Int {

        values.forEachIndexed { index, solution ->

            if (solution.id == item?.id ) return index
        }

        return -1
    }
}