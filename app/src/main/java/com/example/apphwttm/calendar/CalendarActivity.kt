package com.example.apphwttm.calendar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarDateTxt: TextView
    private lateinit var myCalendar: CalendarView
    private lateinit var calendarBtn: Button
    private lateinit var txtViewColor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        //Log.d("TESTSET",myDateInTH().calendarDateInTH())
        //var testTextCalendar: String = ""
        calendarDateTxt = findViewById(R.id.textViewCalendar1)
        var toDayArr = myDateInTH().calendarDateInTH().split(" ")
        var day = toDayArr[0]
        var dayNumArr = myDateInTH().myDateTodayInTHfun().split(" ")
        var dayNum = dayNumArr[0]
        calendarDateTxt.text = "วันนี้วัน $day ที่ $dayNum"
        //setAreYouOk2Date()
        myCalendar = findViewById(R.id.calendarView)
        var keySend: String = getKeyFromCalendar()
        //color
        txtViewColor = findViewById(R.id.textViewCalendar4)
        //btn
        calendarBtn = findViewById(R.id.calendar_btn)
        val key: String = myDateInTH().myDateTodayInTHfun()
        //calendarBtn.isEnabled = checkData(key) != "null"
        if (checkData(key) == "null") {
            calendarBtn.isEnabled = false
            txtViewColor.setBackgroundResource(R.drawable.red)
            txtViewColor.text = "ไม่มีข้อมูลสำหรับวันนี้"
        } else {
            calendarBtn.isEnabled = true
            txtViewColor.setBackgroundResource(R.drawable.green)
            txtViewColor.text = "มีข้อมูล สามากดดูข้อมูลได้ที่ปุ่มด้านล่าง"
        }
        calendarBtn.setOnClickListener {
            if (checkData(key) == "GOOD") {
                val intent = Intent(this, CalendarDetailGoodActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            } else {
                val intent = Intent(this, CalendarDetailBadActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra("key_to_bad",keySend)
                startActivity(intent)
            }
        }



        myCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            val msg = "Selected date is $dayOfMonth $month $year"
//            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
            //val key: String = myDateInTH().myDateTodayInTHfun()
            var myM: String = changeMonthToTH((month + 1).toString())
            var myY: String = changeYearToTH(year.toString())
            val key = "$dayOfMonth $myM $myY"
            if (checkData(key) == "null") {
                calendarBtn.isEnabled = false
                txtViewColor.setBackgroundResource(R.drawable.red)
                txtViewColor.text = "ไม่มีข้อมูลสำหรับวันนี้"
            } else {
                calendarBtn.isEnabled = true
                txtViewColor.setBackgroundResource(R.drawable.green)
                txtViewColor.text = "มีข้อมูล สามากดดูข้อมูลได้ที่ปุ่มด้านล่าง"
            }
            calendarBtn.setOnClickListener {
                if (checkData(key) == "GOOD") {
                    val intent = Intent(this, CalendarDetailGoodActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, CalendarDetailBadActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.putExtra("key_to_bad",keySend)
                    startActivity(intent)
                }
            }
        }


    }


    private fun checkData(toDayKey: String): String {
        val shPData = getSharedPreferences("USERDATAWITHDETAIL", Context.MODE_PRIVATE)
        return shPData.getString(toDayKey, null).toString()

    }

    private fun getKeyFromCalendar(): String {
        var key = myDateInTH().myDateTodayInTHfun()
        myCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var myM: String = changeMonthToTH((month + 1).toString())
            var myY: String = changeYearToTH(year.toString())
            key = "$dayOfMonth $myM $myY"
        }
        return key
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
        calendarDateTxt = findViewById(R.id.textViewCalendar1)
        val toDay = "$day $monthText $year"
        calendarDateTxt.text = toDay
    }

    private fun changeMonthToTH(month: String): String {
        var value = ""
        when (month) {
            "1" -> value = "มกราคม"
            "2" -> value = "กุมภาพันธ์"
            "3" -> value = "มีนาคม"
            "4" -> value = "เมษายน"
            "5" -> value = "พฤษภาคม"
            "6" -> value = "มิถุนายน"
            "7" -> value = "กรกฎาคม"
            "8" -> value = "สิงหาคม"
            "9" -> value = "กันยายน"
            "10" -> value = "ตุลาคม"
            "11" -> value = "พฤศจิกายน"
            "12" -> value = "ธันวาคม"
            else -> {
                value = "ธันวาคม"
            }
        }
        return value
    }

    private fun changeYearToTH(year: String): String {
        return (year.toInt() + 543).toString()
    }

    private fun testShP() {
        val shP1 = getSharedPreferences("name1", Context.MODE_PRIVATE)
        val shP1Edit = shP1.edit()
        shP1Edit.apply {
            putString("KEY", "eiei1")
        }.apply()

        val shP2 = getSharedPreferences("name2", Context.MODE_PRIVATE)
        val shP2Edit = shP2.edit()
        shP2Edit.apply {
            putString("KEY", "eiei2")
        }.apply()
        var test1 = shP1.getString("KEY", null)
        var test2 = shP2.getString("KEY", null)
        Log.d("TESTSHP", test1.toString())
        Log.d("TESTSHP", test2.toString())
    }

}