package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val saveButton : ImageButton = findViewById(R.id.saveButton)
        val discardButton : ImageButton = findViewById(R.id.discardButton)

        saveButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.multiAutoCompleteTextView).text.toString()
            var user = User(name)
            DataBaseHandler(this).insertData(user)
            startActivity(Intent(this, MainActivity::class.java))
        }

        discardButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}