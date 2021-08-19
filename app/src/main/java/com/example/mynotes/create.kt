package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val saveButton : ImageButton = findViewById(R.id.saveButton)
        val discardButton : ImageButton = findViewById(R.id.discardButton)

        saveButton.setOnClickListener {
            val dateTime = LocalDateTime.now()
            val datatext = findViewById<EditText>(R.id.multiAutoCompleteTextView).text.toString()
            val dataDateTime = dateTime.format(DateTimeFormatter.ofPattern("M/d/y")).toString()
            val dataTime = dateTime.format(DateTimeFormatter.ofPattern("H:m")).toString()
            var user = User(datatext, dataDateTime, dataTime)
            DataBaseHandler(this).insertData(user)
            startActivity(Intent(this, MainActivity::class.java))
        }

        discardButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}