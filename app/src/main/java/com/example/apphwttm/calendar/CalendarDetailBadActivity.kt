package com.example.apphwttm.calendar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH

class CalendarDetailBadActivity : AppCompatActivity() {

    private lateinit var txtViewName: TextView
    private lateinit var txtViewDate: TextView
    private lateinit var txtViewBadDisease: TextView
    private lateinit var txtViewDetailHeader: TextView
    private lateinit var txtViewDetail: TextView

    private lateinit var savePicBtn: Button
    private lateinit var shareBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_detail_bad)


        var key: String? = intent.getStringExtra("key_to_bad")
        //Log.d("TESTSET", key.toString())
        var shPWithDetail = getSharedPreferences("USERDATAWITHDETAIL", Context.MODE_PRIVATE)
        var shPWithDetailData = shPWithDetail.getString(key, null)
        //Log.d("TESTSET", shPWithDetailData.toString())
        var str1 = shPWithDetailData!!.split("+")
        var detailTxt = str1?.last()
        var myList: String = str1[0]
        var myListTxt = myList.split("-")
        var size = myListTxt.size - 1
        var setTextmyList = ""
        for (temp in myListTxt)
            if (temp == myListTxt[myListTxt.size-1]){
                continue
            }else{
                setTextmyList += "$temp "
            }
        //Log.d("TESTSET", setTextmyList)

        //name
        txtViewName = findViewById(R.id.txtViewNameB)
        var shPName = getSharedPreferences("userName", Context.MODE_PRIVATE)
        var shPNameData = shPName.getString("userName", null)
        txtViewName.text = shPNameData

        //date
        txtViewDate = findViewById(R.id.textViewDateB)
        var dayArr = myDateInTH().calendarDateInTH().split(" ")
        var dayNumArr = myDateInTH().myDateTodayInTHfun().split(" ")
        var setDate = "${dayArr[0]} ที่ ${dayNumArr[0]} ${dayArr[1]} ${dayArr[2]}"
        txtViewDate.text = setDate

        //lisiOfdisease
        txtViewBadDisease = findViewById(R.id.textViewBadDisease)
        txtViewBadDisease.text = setTextmyList

        //header
        txtViewDetailHeader = findViewById(R.id.textView5)
        txtViewDetailHeader.text = "รายละเอียดเพิ่มเติม"

        //detail
        txtViewDetail = findViewById(R.id.textView6)
        txtViewDetail.text = detailTxt
        //btn savepic
        savePicBtn = findViewById(R.id.buttonBad1)

        //btn share
        shareBtn = findViewById(R.id.buttonBad2)

    }
}