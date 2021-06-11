package com.example.reminderapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val isReminder = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.reminderAdd)
        addButton.setOnClickListener {
            Log.d("Main Activity" ,"THis is the way ")
            sendNotification()
            val i = Intent(this , ReminderList::class.java)
            startActivity(i)
        }
    }

    fun reminderExit(view: View) {
        finish()
    }

    private fun sendNotification() {

    //1 . Get reference of notification manager
        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        //2 . Create NOtification .
        val builder : Notification.Builder

        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.O){

            Log.d("Main Activity" ,"THis is the way23 ")

            val channel = NotificationChannel("channel1" , "Reminder App" ,NotificationManager.IMPORTANCE_DEFAULT)

            nManager.createNotificationChannel(channel)

            builder = Notification.Builder(this , "channel1" )
        }else{
            builder = Notification.Builder(this)
        }
        // 2. icon // title // shot description.
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("reminder Notication")
        builder.setContentText("Reminder added")


        val i  = Intent(this , MainActivity::class.java)
        val pendingi = PendingIntent.getActivity(this,0 , i , 0)
        builder.setContentIntent(pendingi)

        val myNotification = builder.build()
        myNotification.flags = Notification.FLAG_AUTO_CANCEL

        // Show Notification

        nManager.notify(1,myNotification)
        Log.d("Main Activity ", "This is the Way1")


    }
}



















