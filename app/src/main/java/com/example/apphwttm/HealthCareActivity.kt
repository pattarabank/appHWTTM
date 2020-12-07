package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate

class HealthCareActivity : AppCompatActivity() {


    private lateinit var bottomBar: BottomNavigationView
    private lateinit var healthcare_date : TextView


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






    }

    private fun setHealthCareDate(){
        var day : String
        var month : String
        var year : String
        val date = LocalDate.now().toString().split("-")
        day = date[2]
        year = (date[0].toInt() + 543).toString()
        month = date[1]
        var monthText = ""
        when(month){
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
        healthcare_date = findViewById(R.id.healthcare_tx_2)
        healthcare_date.text = day +" "+ monthText +" "+ year
    }



}