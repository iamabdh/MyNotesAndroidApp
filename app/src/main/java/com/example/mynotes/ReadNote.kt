package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.Exception

class ReadNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_note)


        val dataText : TextView = findViewById(R.id.data_text)
        val dataDateTime : TextView = findViewById(R.id.data_dateTime)
        val dataTime : TextView = findViewById(R.id.data_time)
        val backButton : ImageButton = findViewById(R.id.backButton)
        val msg = intent.getStringExtra("id").toString()
        val listUser: MutableList<User> = DataBaseHandler(this).readData()
        try {
            dataText.text = listUser[msg.toInt()].text
            dataDateTime.text = listUser[msg.toInt()].dateTime
            dataTime.text = listUser[msg.toInt()].time
        } catch (e : Exception) {
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
        }
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}