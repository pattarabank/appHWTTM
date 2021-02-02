package com.example.apphwttm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
//        Handler().postDelayed({
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)

//        Handler(Looper.getMainLooper()).postDelayed({
//            // Your Code
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)

        //test
        val sharedPreferences = getSharedPreferences("sharedPrefsName", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.apply {
//            putString("sharedPrefsName", "-")
//        }.apply()

        val isBlank = sharedPreferences.getString("sharedPrefsName", null)
        Log.d("TEST_SP", isBlank.toString())
        if (isBlank == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}