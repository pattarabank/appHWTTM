package com.example.apphwttm.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import com.example.apphwttm.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    private lateinit var CalendarDateTxt: TextView
    private lateinit var myCalendar: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        //var testTextCalendar: String = ""
        CalendarDateTxt = findViewById(R.id.textViewCalendar1)
        //setAreYouOk2Date()
        myCalendar = findViewById(R.id.calendarView)

        myCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "Selected date is " + dayOfMonth + " " + month + " " + year
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
        }


    }

    private fun setAreYouOk2Date() {
        var day: String
        var month: String
        var year: String
        val date = LocalDate.now().toString().split("-")
        day = date[2]
        year = (date[0].toInt() + 543).toString()
        month = date[1]
        var monthText = ""
        when (month) {
            "01" -> monthText = "มกราคม"
            "02" -> monthText = "กุมภาพันธ์"
            "03" -> monthText = "มีนาคม"
            "04" -> monthText = "เมษายน"
            "05" -> monthText = "พฤษภาคม"
            "06" -> monthText = "มิถุนายน"
            "07" -> monthText = "กรกฎาคม"
            "08" -> monthText = "สิงหาคม"
            "09" -> monthText = "กันยายน"
            "10" -> monthText = "ตุลาคม"
            "11" -> monthText = "พฤศจิกายน"
            "12" -> monthText = "ธันวาคม"
            else -> {
                monthText = "ธันวาคม"
            }
        }
        CalendarDateTxt = findViewById(R.id.textViewCalendar1)
        val toDay = "$day $monthText $year"
        CalendarDateTxt.text = toDay
    }

}