package com.example.reminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FrontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)

        val eButton = findViewById<Button>(R.id.enterButton)

        eButton.setOnClickListener {
            val i = Intent(this , MainActivity::class.java)
            startActivity(i)

        }

    }

}