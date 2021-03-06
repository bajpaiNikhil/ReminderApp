package com.example.reminderapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import java.util.*

class MyRemDialog : androidx.fragment.app.DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        lateinit var myDlg : Dialog

        val parent = activity
        val args = arguments


        var mytitle = args?.getString("title")
        var message = args?.getString("msg")
        val dlgType = args?.getInt("type")

        var builder = AlertDialog.Builder(parent)
        when(dlgType){
            1 ->{
                //do someThing for time dialog
                myDlg = TimePickerDialog(parent, parent as TimePickerDialog.OnTimeSetListener , 6,0,false)

            }

            2 ->{
                //do something for date dialog
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)

                myDlg = DatePickerDialog(parent!! , parent as DatePickerDialog.OnDateSetListener, year, month, day)
            }

            3 ->{
                //do somethings for pop up dialog

                builder.setTitle(mytitle)
                builder.setMessage(message)

                builder.setPositiveButton("YEs" , DialogInterface.OnClickListener { dialog, which ->

                    (parent as MainActivity).sendNotification() //called custom Notification funtion

                    val event = parent.titleReminder.text.toString()
                    val time  = parent.timeTImeView.text.toString()
                    val date  = parent.dateTextView.text.toString()

                    val eventCreate = Events(event , time , date)
                    eventList.add(eventCreate)

//                    val intent = Intent(Intent.ACTION_INSERT).apply {
//
//                        data = CalendarContract.Events.CONTENT_URI
//                        //Toast.makeText(this, "Reached till here" , Toast.LENGTH_SHORT)
//                        putExtra(CalendarContract.Events.TITLE, event)
//
//                    }
//                        startActivity(intent)

                    val i = Intent(parent!!,CustomReminderList::class.java)
                    startActivity(i)
                })

                builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })

                builder.setIcon(R.drawable.ic_launcher_foreground)
                myDlg = builder.create()

            }

        }
        return myDlg
    }
}