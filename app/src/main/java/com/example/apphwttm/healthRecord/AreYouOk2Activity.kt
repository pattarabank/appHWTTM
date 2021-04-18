package com.example.apphwttm.healthRecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.apphwttm.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.time.LocalDate

class AreYouOk2Activity : AppCompatActivity() {

    private lateinit var areYouOk2Date: TextView
    private lateinit var areYouOk2Btn1: ExtendedFloatingActionButton
    private lateinit var areYouOk2Btn2: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_are_you_ok2)
        setAreYouOk2Date()
        //btn
        areYouOk2Btn1 = findViewById(R.id.areyouok2_btn_1)
        areYouOk2Btn1.setOnClickListener {
            val intent = Intent(this,RelateDiseaseActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        areYouOk2Btn2 = findViewById(R.id.areyouok2_btn_2)
        areYouOk2Btn2.setOnClickListener {
            val intent = Intent(this,AddRecordDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        val arrayAdapter: ArrayAdapter<*>
        val dataList: ArrayList<String> =
            intent.getSerializableExtra("are_you_ok_data") as ArrayList<String>
        var myListView = findViewById<ListView>(R.id.areyouok2_listview)
//        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)
        arrayAdapter = ArrayAdapter(this, R.layout.custom_listview, dataList)
        myListView.adapter = arrayAdapter

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
        areYouOk2Date = findViewById(R.id.areyouok_date_txt)
        val toDay = "$day $monthText $year"
        areYouOk2Date.text = toDay
    }
}