package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class RecyclerAdapter(context: Context, private val dataObject : MutableList<User>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val context = context

    override fun getItemCount(): Int {
        return dataObject.size
    }
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item : User = dataObject[position]
        holder.dataText.text = item.name
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var dataText : TextView

        init {
            dataText = itemView.findViewById(R.id.data_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

}