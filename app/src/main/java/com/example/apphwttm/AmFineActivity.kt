package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AmFineActivity : AppCompatActivity() {

    private lateinit var closeIcon : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_am_fine)

        closeIcon = findViewById(R.id.amfine_close_icon)
        //กดฉันสบายดี
        closeIcon.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    }
}