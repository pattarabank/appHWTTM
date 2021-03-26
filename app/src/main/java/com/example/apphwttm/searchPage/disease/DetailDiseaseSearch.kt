package com.example.apphwttm.searchPage.disease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.R

class DetailDiseaseSearch : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_disease_search)

        var bundle: Bundle? = intent.extras
        var detail_disease_name = bundle!!.getString("send_to_detail_disease_name")
        var detail_disease_des = bundle!!.getString("send_to_detail_disease_des")

        val textviewName: TextView = findViewById(R.id.detail_disease_name)
        val textviewdes: TextView = findViewById(R.id.detail_disease_des)
        val previousicondisease : TextView = findViewById(R.id.myPreviousIconDisease)

        textviewName.text = detail_disease_name
        textviewdes.text = detail_disease_des
        previousicondisease.setOnClickListener {
            val intent = Intent(this,SecActivityDiseaseSearch::class.java)
            startActivity(intent)
        }


    }
}