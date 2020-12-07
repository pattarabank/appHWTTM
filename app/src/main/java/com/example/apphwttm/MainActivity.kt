package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


//    private lateinit var previousIcon : TextView
//    private lateinit var bottomBar : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        previousIcon = findViewById(R.id.myPreviousIcon)
//        previousIcon.setOnClickListener { view->
//            val myStackBar = Snackbar.make(view,"TEST CLICKED",Snackbar.LENGTH_LONG)
//            myStackBar.show()
//        }

//        bottomBar = findViewById(R.id.bottom_navigation)
//        bottomBar.selectedItemId = R.id.itemHome
//        bottomBar.setOnNavigationItemSelectedListener { item->
//            when(item.itemId){
//                R.id.itemHome -> {
//
//                    true
//                }
//                R.id.itemSearch -> {
//                    val intent = Intent(this,SearchActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//                    startActivity(intent)
//                    true
//                }
//                R.id.itemHealthCare -> {
//
//                    true
//                }
//                R.id.itemProfile -> {
//
//                    true
//                }
//                else -> false
//            }
//        }



    }
}