package com.example.mynotes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.lang.Exception
import java.util.*

class RecyclerAdapter(context: Context, private val dataObject : MutableList<User>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val context = context

    override fun getItemCount(): Int {
        return dataObject.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item : User = dataObject[position]

        // check how many words in text
        // only home page shows 20 words only

        val strArr =  item.text.split(" ")
        var toStr : String = ""
        if (strArr.size <= 20){
            holder.dataText.text = item.text
        } else {
            for (i in 0..21 ){
                toStr = toStr + strArr[i] + " "
            }
            holder.dataText.text = toStr + "..."

        }
        holder.dataDateTime.text = item.dateTime
        holder.dataTime.text = item.time


    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var dataText : TextView
        var dataDateTime : TextView
        var dataTime : TextView

        init {
            dataText = itemView.findViewById(R.id.data_text)
            dataDateTime = itemView.findViewById(R.id.data_dateTime)
            dataTime = itemView.findViewById(R.id.data_time)

            itemView.setOnClickListener{
                val position : Int = absoluteAdapterPosition
                try {
                    itemView.context.startActivity(Intent(itemView.context, ReadNote::class.java))
                } catch (e:Exception){
                    Toast.makeText(itemView.context, e.toString(), Toast.LENGTH_LONG).show()
                }
//                Toast.makeText(itemView.context, "$position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

}