package com.example.apphwttm.healthRecord

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class HealthCareActivity : AppCompatActivity() {


    private lateinit var bottomBar: BottomNavigationView
    private lateinit var healthcareDate: TextView
    private lateinit var iconWell: TextView//ไอคอนยิ้ม
    private lateinit var iconSick: TextView//ไอคอนหน้าบึ้ง

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_care)
        setHealthCareDate()

        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.selectedItemId = R.id.itemHealthCare
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.itemSearch -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.itemHealthCare -> {

                    true
                }
                R.id.itemProfile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val toDayKey: String = myDateInTH().myDateTodayInTHfun()
        val userHealthCareLimitPerDay = getSharedPreferences("userLimitPerDay", Context.MODE_PRIVATE)
        val isOk = userHealthCareLimitPerDay.getString(toDayKey, null)

        iconWell = findViewById<TextView>(R.id.healthcare_tx_3)
        iconWell.setOnClickListener {
            //Snackbar.make(it, "am fine", Snackbar.LENGTH_SHORT).show()

            val dateKey: String = myDateInTH().myDateTodayInTHfun()
            if (isOk == null){//วันนนี้ยังไม่ได้บันทึก
                //บันทึกว่าวันนี้ บันทึกไปแล้ว และ บันทึกข้อมูลว่าสบายดี
                val userHealthCareLimitPerDay = getSharedPreferences("userLimitPerDay", Context.MODE_PRIVATE)
                val editLimit = userHealthCareLimitPerDay.edit()
                editLimit.apply{
                    putString(toDayKey,"full")
                }.apply()

                val dataTodayWithDetail = getSharedPreferences("USERDATAWITHDETAIL", Context.MODE_PRIVATE)
                val edit = dataTodayWithDetail.edit()
                edit.apply {
                    putString(dateKey,"GOOD")
                }.apply()

                val intent = Intent(this, AmFineActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }else{
                Snackbar.make(it,"วันนี้บันทึกไปแล้ว",Snackbar.LENGTH_SHORT).show()
            }

        }
        iconSick = findViewById<TextView>(R.id.healthcare_tx_4)
        iconSick.setOnClickListener {
            //Snackbar.make(it, "not fine", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this, AreYouOkActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)

        }

    }

    private fun setHealthCareDate() {
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
        healthcareDate = findViewById(R.id.healthcare_tx_2)
        val toDay = "$day $monthText $year"
        healthcareDate.text = toDay
    }


}