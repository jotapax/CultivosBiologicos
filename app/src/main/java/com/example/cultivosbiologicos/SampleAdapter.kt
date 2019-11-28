package com.example.cultivosbiologicos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sample.view.*

class SampleAdapter(val ctx : Context, val list : List<Sample>) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(sample : Sample, activity: MuestrasActivity) {

            itemView.patient.text = sample.patient
            itemView.culture.text = sample.culture
            itemView.solution.text = sample.solution.solution

            itemView.cardView.setOnClickListener {

                activity.manageSample(sample)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.sample, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], ctx as MuestrasActivity)
    }

    override fun getItemCount(): Int = list.size

}