package com.example.apphwttm.searchPage.firstaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apphwttm.R

class DetailFirstAidSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_first_aid_search)

        var textviewname: TextView = findViewById(R.id.detail_firstaid_name)
        var textviewdes: TextView = findViewById(R.id.detail_firstaid_des)
        var previousiconfirstaid: TextView = findViewById(R.id.myPreviousIconFirstaid)

        var bundle: Bundle? = intent.extras
        var detail_firstaid_name = bundle!!.getString("send_to_detail_firstaid_name")
        var detail_firstaid_des = bundle!!.getString("send_to_detail_firstaid_des")

        textviewname.text = detail_firstaid_name
        textviewdes.text = detail_firstaid_des
        previousiconfirstaid.setOnClickListener {
            val intent = Intent(this,ActivityFirstAidSearch::class.java)
            startActivity(intent)
        }

    }
}