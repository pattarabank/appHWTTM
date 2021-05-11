package com.example.apphwttm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.admin.AdminActivity
import com.example.apphwttm.calendar.CalendarActivity
import com.example.apphwttm.healthRecord.HealthCareActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ProfileActivity : AppCompatActivity() {


    private lateinit var bottomBar: BottomNavigationView
    private lateinit var calendarBtn: ExtendedFloatingActionButton
//    private lateinit var settingsBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //ใส่ข้อมูลผู้ใช้
        val sharedPreferencesName = getSharedPreferences("userName", Context.MODE_PRIVATE)
        val getUserName = sharedPreferencesName.getString("userName", null)
        val userNameTextView: TextView = findViewById(R.id.profileUserName)
        userNameTextView.text = getUserName


        val sharedPreferencesBirthDate = getSharedPreferences("userYear", Context.MODE_PRIVATE)
        val getUserBirthDate = sharedPreferencesBirthDate.getString("userYear", null)
        val userBirthDateTextView: TextView = findViewById(R.id.profileBirthDate)
        userBirthDateTextView.text = getUserBirthDate

//        val sharedPreferencesDisease = getSharedPreferences("sharedPrefsDisease",Context.MODE_PRIVATE)
//        val getUserDisease = sharedPreferencesDisease.getString("sharedPrefsDisease",null)
//        val userDiseaseTextView : TextView = findViewById(R.id.profileDisease)
//        userDiseaseTextView.text = getUserDisease


        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.selectedItemId = R.id.itemProfile
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
                    val intent = Intent(this, HealthCareActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.itemProfile -> {
                    true
                }
                else -> false
            }
        }
        calendarBtn = findViewById(R.id.profile_btn_1)
        calendarBtn.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

//        settingsBtn = findViewById(R.id.settings_btn)
//        settingsBtn.setOnClickListener {
//            val intent = Intent(this,AdminActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            startActivity(intent)
//        }

    }
}