package com.example.apphwttm.searchPage.herb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.R

class DetailHerbSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_herb_search)


        val textviewname: TextView = findViewById(R.id.detail_herb_name)
        val textviredes: TextView = findViewById(R.id.detail_herb_des)
        val previousiconherb: TextView = findViewById(R.id.myPreviousIconHerb)

        var bundle: Bundle? = intent.extras
        var detail_herb_name = bundle!!.getString("send_to_detail_herb_name")
        var detail_herb_des = bundle!!.getString("send_to_detail_herb_des")

        textviewname.text = detail_herb_name
        textviredes.text = detail_herb_des
        previousiconherb.setOnClickListener {
            val intent = Intent(this, ActivityHerbSearch::class.java)
            startActivity(intent)
        }


    }
}