package com.example.reminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReminderList : AppCompatActivity() {
    companion object{
        const val Event = "Event"
        const val Time  = "Time"
        const val Date  = "Date"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_list)

        val TextEvent = findViewById<TextView>(R.id.showEvent)
        val dateEvent = findViewById<TextView>(R.id.showDate)
        val timeEvent = findViewById<TextView>(R.id.showTIme)

        val TxtEvent = intent.getStringExtra(Event)
        val TxtDate = intent.getStringExtra(Date)
        val TxtTIme = intent.getStringExtra(Time)


        TextEvent.text = TxtEvent
        dateEvent.text = TxtDate
        timeEvent.text = TxtTIme

    }
}