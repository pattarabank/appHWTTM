package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecActivityDiseaseSearch : AppCompatActivity() {


    private lateinit var diseaseSearchBackBtn : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec_disease_search)


        diseaseSearchBackBtn = findViewById(R.id.myPreviousIcon)
        diseaseSearchBackBtn.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }



    }
}