package com.example.apphwttm.searchPage.disease

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.disease.fragments.NewDetailDisease1Fragment
import com.example.apphwttm.searchPage.disease.fragments.NewDetailDisease2Fragment
import com.example.apphwttm.searchPage.disease.fragments.ViewPagerNewDetailAdapter
import com.google.android.material.tabs.TabLayout

class NewDetailDiseaseSearchActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabsLayout: TabLayout

    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail_disease_search)

        var bundle: Bundle? = intent.extras
        var detail_disease_name = bundle!!.getString("send_to_detail_disease_name")
        var detail_disease_des = bundle!!.getString("send_to_detail_disease_des")

        Log.d("TEST_STR", detail_disease_des.toString())

        var detail_disease_des_kid = bundle!!.getString("send_to_detail_disease_des_kid")

        Log.d("TEST_STR", detail_disease_des_kid.toString())

        textView1 = findViewById(R.id.textViewNewDisease)
        textView1.text = detail_disease_name


        setUpTabs()


    }

    private fun setUpTabs() {
        val adapter = ViewPagerNewDetailAdapter(supportFragmentManager)
        adapter.addFragment(NewDetailDisease1Fragment(), "ผู้ใหญ่")
        adapter.addFragment(NewDetailDisease2Fragment(), "เด็ก")
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        tabsLayout = findViewById(R.id.tabLayout)
        tabsLayout.setupWithViewPager(viewPager)


    }



}

