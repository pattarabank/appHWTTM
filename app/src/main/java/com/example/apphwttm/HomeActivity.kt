package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomBar : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.selectedItemId = R.id.itemHome
        bottomBar.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.itemHome -> {

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
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


    }
}