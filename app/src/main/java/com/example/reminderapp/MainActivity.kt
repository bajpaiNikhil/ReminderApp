package com.example.reminderapp

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.time.Month

class MainActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {


    lateinit var dateTextView : TextView
    lateinit var timeTImeView : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.reminderAdd)
        dateTextView  = findViewById(R.id.reminderDate)
        timeTImeView  = findViewById(R.id.reminderTime)

        addButton.setOnClickListener {
            Log.d("Main Activity" ,"THis is the way for dialog box ")

            dialogBox()

            sendNotification()
//            val i = Intent(this , ReminderList::class.java)
//            startActivity(i)
        }

    }

    private fun dialogBox() {

        Log.d("MainActivity","suppose dialog box")

        val dlg = MyRemDialog()
        val b = Bundle()

        b.putString("title", "ADD REMINDER! ?")
        b.putString("msg" , "do you want to add reminder ?")

        b.putInt("type" , 3)
        dlg.arguments = b
        dlg.show(supportFragmentManager,"3")

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
    fun timeClick(view: View) {
        val dlg = MyRemDialog()
        val b = Bundle()
        b.putInt("type" , 1)

        dlg.arguments = b
        dlg.show(supportFragmentManager,"1")


    }

    fun dateClick(view: View) {
        val dlg = MyRemDialog()
        val b = Bundle()
        b.putInt("type" , 2)

        dlg.arguments = b
        dlg.show(supportFragmentManager, "2")

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        Log.d("MainActivity" , "here is the way Date")
        val date = "$dayOfMonth - ${month+1} - $year"
        dateTextView.text = date

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        Log.d("MainActivity" , "there is the way TIme")

        val time = "$hourOfDay : $minute "
        timeTImeView.text = time

    }




}



















