package com.example.apphwttm.healthRecord

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.util.function.Consumer

class AreYouOk2Activity : AppCompatActivity() {

    private lateinit var areYouOk2Date: TextView
    private lateinit var areYouOk2Btn1: ExtendedFloatingActionButton
    private lateinit var areYouOk2Btn2: ExtendedFloatingActionButton
    private lateinit var checkBtn: TextView
    private lateinit var areYouOk2BackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_are_you_ok2)

        //set back btn
        areYouOk2BackBtn = findViewById(R.id.myPreviousIconAreyouok2)
        areYouOk2BackBtn.setOnClickListener {
            onBackPressed()
        }

        setAreYouOk2Date()
        //รับโรคจาก chip มาแสดง
        val arrayAdapter: ArrayAdapter<*>
        val dataList: ArrayList<String> =
            intent.getSerializableExtra("are_you_ok_data") as ArrayList<String>
        var myListView = findViewById<ListView>(R.id.areyouok2_listview)
//        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)
        arrayAdapter = ArrayAdapter(this, R.layout.custom_listview, dataList)
        myListView.adapter = arrayAdapter

        //send data to bad calendar
//        var temp: String = ""
//        dataList.forEach{
//            temp+="$it "
//        }
//        val shPAreYouOkData = getSharedPreferences("are_you_ok_data_to_badCal",Context.MODE_PRIVATE)
//        var editshPAreYouOkData = shPAreYouOkData.edit()
//        editshPAreYouOkData.apply{
//            putString("are_you_ok_data_to_badCal_key",temp)
//        }.apply()

        //btn
        //โรคที่เกี่ยวข้อง btn
        areYouOk2Btn1 = findViewById(R.id.areyouok2_btn_1)
        areYouOk2Btn1.setOnClickListener {
            //val sendDataToRelate: ArrayList<String> =
            //    intent.getSerializableExtra("are_you_ok_data") as ArrayList<String>
            val intent = Intent(this, RelateDiseaseActivity::class.java)
            intent.putExtra("SEND_DATA_TO_RELATE", dataList)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //บันทึกรายละเอียดเพิ่มเติม btn
        areYouOk2Btn2 = findViewById(R.id.areyouok2_btn_2)
        areYouOk2Btn2.setOnClickListener {
            val intent = Intent(this, AddRecordDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        checkBtn = findViewById(R.id.check_icon)
        checkBtn.setOnClickListener {
            val toDayKey: String = myDateInTH().myDateTodayInTHfun()
            val userHealthCareLimitPerDay =
                getSharedPreferences("userLimitPerDay", Context.MODE_PRIVATE)
            val isOk = userHealthCareLimitPerDay.getString(toDayKey, null)
            if (isOk == null) {
                val userHealthCareLimitPerDay =
                    getSharedPreferences("userLimitPerDay", Context.MODE_PRIVATE)
                val editLimit = userHealthCareLimitPerDay.edit()
                editLimit.apply {
                    putString(toDayKey, "full")
                }.apply()
                //Snackbar.make(it,"บันทึกข้อมูลสุขภาพวันนี้สำเร็จ",Snackbar.LENGTH_SHORT).show()
                Toast.makeText(this, "บันทึกข้อมูลสุขภาพวันนี้สำเร็จ", Toast.LENGTH_LONG).show()

                val addedSharedPreferences =
                    getSharedPreferences("additionalData", Context.MODE_PRIVATE)
                val resetData = addedSharedPreferences.edit()
                resetData.apply {
                    putString("ADDITIONALDATA", "")
                }.apply()

                val intent = Intent(this, HealthCareActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(it, "วันนี้บันทึกไปแล้ว", Snackbar.LENGTH_SHORT).show()
            }

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
        areYouOk2Date = findViewById(R.id.areyouok_date_txt)
        val toDay = "$day $monthText $year"
        areYouOk2Date.text = toDay
    }
}