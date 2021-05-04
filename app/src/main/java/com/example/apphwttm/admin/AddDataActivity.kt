package com.example.apphwttm.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.apphwttm.R
import com.example.apphwttm.admin.add_disease.AddDiseaseDataActivity
import com.example.apphwttm.admin.add_firstAid.AddFirstAidDataActivity
import com.example.apphwttm.admin.add_herb.AddHerbDataActivity

class AddDataActivity : AppCompatActivity() {

    private lateinit var addHerbBtn : Button
    private lateinit var addDiseaseBtn : Button
    private lateinit var addfirstAidBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        addHerbBtn = findViewById(R.id.add_herb)
        addHerbBtn.setOnClickListener {
            val intent = Intent(this,AddHerbDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        addfirstAidBtn = findViewById(R.id.add_firstaid)
        addfirstAidBtn.setOnClickListener {
            val intent = Intent(this,AddFirstAidDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        addDiseaseBtn = findViewById(R.id.add_disease)
        addDiseaseBtn.setOnClickListener {
            val intent = Intent(this,AddDiseaseDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


    }
}