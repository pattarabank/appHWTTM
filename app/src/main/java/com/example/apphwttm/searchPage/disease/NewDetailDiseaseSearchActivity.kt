package com.example.apphwttm.searchPage.disease

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.head.DiseaseHeadActivity
import com.example.apphwttm.searchPage.relate_herb.RelateHerbActivity
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class NewDetailDiseaseSearchActivity : AppCompatActivity() {

    private lateinit var tabsLayout2: TabLayout

    private lateinit var textView1: TextView
    private lateinit var testTextView: TextView

    private lateinit var textViewBackBtn: TextView
    private lateinit var relateHerbBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail_disease_search)

        var bundle: Bundle? = intent.extras
        var detail_disease_name = bundle!!.getString("send_to_detail_disease_name")
        var detail_disease_des = bundle!!.getString("send_to_detail_disease_des")
        //Log.d("TEST_STR", detail_disease_des.toString())
        var detail_disease_des_kid = bundle!!.getString("send_to_detail_disease_des_kid")
        //Log.d("TEST_STR", detail_disease_des_kid.toString())
        val detail_disease_id = bundle!!.getString("send_to_detail_disease_id")

        textView1 = findViewById(R.id.textViewNewDisease)
        textView1.text = detail_disease_name

        //test
        tabsLayout2 = findViewById(R.id.tabLayout2)
        testTextView = findViewById(R.id.test_textview)
        val getIsKid = getSharedPreferences("isKid", Context.MODE_PRIVATE)
        val isKid = getIsKid.getBoolean("isKid", false)
        if (isKid) {
            tabsLayout2.getTabAt(0)?.text = "เด็ก"
            tabsLayout2.getTabAt(1)?.text = "ผู้ใหญ่"
            testTextView.text = detail_disease_des_kid
            tabsLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //Log.d("TESTTAG",tab.toString())
                    if (tab?.position == 0) {
                        //Log.d("TESTTAG","TAB1")
                        tab.text = "เด็ก"
                        testTextView.text = detail_disease_des_kid
                    } else {
                        //Log.d("TESTTAG","TAB2")
                        if (tab != null) {
                            tab.text = "ผู้ใหญ่"
                        }
                        testTextView.text = detail_disease_des
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        } else {
            tabsLayout2.getTabAt(0)?.text = "ผู้ใหญ่"
            tabsLayout2.getTabAt(1)?.text = "เด็ก"
            testTextView.text = detail_disease_des
            tabsLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //Log.d("TESTTAG",tab.toString())
                    if (tab?.position == 0) {
                        //Log.d("TESTTAG","TAB1")
                        tab.text = "ผู้ใหญ่"
                        testTextView.text = detail_disease_des
                    } else {
                        //Log.d("TESTTAG","TAB2")
                        if (tab != null) {
                            tab.text = "เด็ก"
                        }
                        testTextView.text = detail_disease_des_kid
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        //relate herb
        relateHerbBtn = findViewById(R.id.relate_herb_btn)
        relateHerbBtn.setOnClickListener {
            val intent = Intent(this, RelateHerbActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("disease_des", detail_disease_des)
            intent.putExtra("disease_des_kid", detail_disease_des_kid)
            startActivity(intent)
        }

        // back btn
        textViewBackBtn = findViewById(R.id.myPreviousIconNewDisease)
        textViewBackBtn.setOnClickListener {
//            val intent = Intent(this, DiseaseHeadActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            startActivity(intent)
            onBackPressed()
            finish()
        }

    }
}

