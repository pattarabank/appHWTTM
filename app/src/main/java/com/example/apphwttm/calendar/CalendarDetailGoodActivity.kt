package com.example.apphwttm.calendar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH

class CalendarDetailGoodActivity : AppCompatActivity() {

    private lateinit var txtViewName: TextView
    private lateinit var txtViewDate: TextView
    private lateinit var txtViewGood: TextView

    private lateinit var txtViewIc1: TextView
    private lateinit var txtViewIc2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_detail_good)

        //name
        val shPName = getSharedPreferences("userName", Context.MODE_PRIVATE)
        var shPNameData = shPName.getString("userName",null)
        txtViewName = findViewById(R.id.txtViewName)
        txtViewName.text = shPNameData

        //date
        txtViewDate = findViewById(R.id.textViewDate)
        var dayArr = myDateInTH().calendarDateInTH().split(" ")
        var dayNumArr = myDateInTH().myDateTodayInTHfun().split(" ")
        var setDate = "${dayArr[0]} ที่ ${dayNumArr[0]} ${dayArr[1]} ${dayArr[2]}"
        txtViewDate.text = setDate

        //good
        txtViewGood = findViewById(R.id.textViewGood)
        txtViewGood.text = "วันนี้คุณสบายดี"

        //Ic
        txtViewIc1 = findViewById(R.id.textViewIcon1)
        txtViewIc2 = findViewById(R.id.textViewIcon2)
        txtViewIc1.text = "ขอให้สุขภาพแข็งแรง"
        //txtViewIc2.setBackgroundResource(R.drawable.embarrassed)




    }
}