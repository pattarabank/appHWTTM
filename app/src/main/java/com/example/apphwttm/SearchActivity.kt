package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.healthRecord.HealthCareActivity
import com.example.apphwttm.searchPage.body.DiseaseBodyActivity
import com.example.apphwttm.searchPage.disease.SecActivityDiseaseSearch
import com.example.apphwttm.searchPage.firstaid.ActivityFirstAidSearch
import com.example.apphwttm.searchPage.head.DiseaseHeadActivity
import com.example.apphwttm.searchPage.herb.ActivityHerbSearch
import com.example.apphwttm.searchPage.lowerbody.DiseaseLowerBodyActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {

    private lateinit var head: TextView
    private lateinit var body: TextView
    private lateinit var lowerBody: TextView

    private lateinit var bottomBar: BottomNavigationView
    private lateinit var diseaseSearchBtn: TextView
    private lateinit var firstaidSearchBtn: TextView
    private lateinit var herbSearchBtn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.selectedItemId = R.id.itemSearch
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                R.id.itemSearch -> {

                    true
                }
                R.id.itemHealthCare -> {
                    val intent = Intent(this, HealthCareActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
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
        //////

        head = findViewById(R.id.myTxv_2)
        head.setOnClickListener {
            var intent = Intent(this,DiseaseHeadActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        body = findViewById(R.id.myTxv_3)
        body.setOnClickListener {
            var intent = Intent(this,DiseaseBodyActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        lowerBody = findViewById(R.id.myTxv_4)
        lowerBody.setOnClickListener {
            var intent = Intent(this,DiseaseLowerBodyActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        diseaseSearchBtn = findViewById(R.id.myTxv_5)
        diseaseSearchBtn.setOnClickListener {
            var intent = Intent(this, SecActivityDiseaseSearch::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        firstaidSearchBtn = findViewById(R.id.myTxv_6)
        firstaidSearchBtn.setOnClickListener {
            var intent = Intent(this, ActivityFirstAidSearch::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        herbSearchBtn = findViewById(R.id.myTxv_7)
        herbSearchBtn.setOnClickListener {
            var intent = Intent(this, ActivityHerbSearch::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

    }


}