package com.example.apphwttm.searchPage.disease

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apphwttm.R
import com.google.android.material.tabs.TabLayout

class NewDetailDiseaseSearchActivity : AppCompatActivity() {

    private lateinit var tabsLayout2: TabLayout

    private lateinit var textView1: TextView
    private lateinit var testTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail_disease_search)

        var bundle: Bundle? = intent.extras
        var detail_disease_name = bundle!!.getString("send_to_detail_disease_name")
        var detail_disease_des = bundle!!.getString("send_to_detail_disease_des")
        //Log.d("TEST_STR", detail_disease_des.toString())
        var detail_disease_des_kid = bundle!!.getString("send_to_detail_disease_des_kid")
        //Log.d("TEST_STR", detail_disease_des_kid.toString())

        textView1 = findViewById(R.id.textViewNewDisease)
        textView1.text = detail_disease_name

        //test
        testTextView = findViewById(R.id.test_textview)
        testTextView.text = detail_disease_des
        tabsLayout2 = findViewById(R.id.tabLayout2)
        tabsLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //Log.d("TESTTAG",tab.toString())
                if (tab?.position == 0) {
                    //Log.d("TESTTAG","TAB1")
                    testTextView.text = detail_disease_des
                } else {
                    //Log.d("TESTTAG","TAB2")
                    testTextView.text = detail_disease_des_kid
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }


}

