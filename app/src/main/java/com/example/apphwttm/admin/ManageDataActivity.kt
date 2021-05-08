package com.example.apphwttm.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.apphwttm.R
import com.example.apphwttm.admin.manage_disease.AddDiseaseDataActivity
import com.example.apphwttm.admin.manage_disease.EditDiseaseDataActivity
import com.example.apphwttm.admin.manage_firstAid.AddFirstAidDataActivity
import com.example.apphwttm.admin.manage_firstAid.EditFirstAidDataActivity
import com.example.apphwttm.admin.manage_herb.AddHerbDataActivity
import com.example.apphwttm.admin.manage_herb.DeleteHerbDataActivity
import com.example.apphwttm.admin.manage_herb.EditHerbDataActivity

class ManageDataActivity : AppCompatActivity() {

    private lateinit var addHerbBtn: Button
    private lateinit var addDiseaseBtn: Button
    private lateinit var addfirstAidBtn: Button

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_data)

        toolbar = findViewById(R.id.data_toolbar)
        addHerbBtn = findViewById(R.id.add_herb)
        addfirstAidBtn = findViewById(R.id.add_firstaid)
        addDiseaseBtn = findViewById(R.id.add_disease)

        addHerbBtn.setOnClickListener {
            val intent = Intent(this, AddHerbDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        addfirstAidBtn.setOnClickListener {
            val intent = Intent(this, AddFirstAidDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        addDiseaseBtn.setOnClickListener {
            val intent = Intent(this, AddDiseaseDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.memu_data_1 -> {
                    addDiseaseBtn.text = "add Disease Data"
                    addHerbBtn.text = "add Herb Data"
                    addfirstAidBtn.text = "add First Aid Data"
                    addHerbBtn.setOnClickListener {
                        val intent = Intent(this, AddHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addfirstAidBtn.setOnClickListener {
                        val intent = Intent(this, AddFirstAidDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addDiseaseBtn.setOnClickListener {
                        val intent = Intent(this, AddDiseaseDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                    true
                }
                R.id.memu_data_2 -> {
                    addDiseaseBtn.text = "edit Disease Data"
                    addHerbBtn.text = "edit Herb Data"
                    addfirstAidBtn.text = "edit First Aid Data"
                    addHerbBtn.setOnClickListener {
                        val intent = Intent(this, EditHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addfirstAidBtn.setOnClickListener {
                        val intent = Intent(this, EditFirstAidDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addDiseaseBtn.setOnClickListener {
                        val intent = Intent(this, EditDiseaseDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                    true
                }
                R.id.memu_data_3 -> {
                    addDiseaseBtn.text = "delete Disease Data"
                    addHerbBtn.text = "delete Herb Data"
                    addfirstAidBtn.text = "delete First Aid Data"
                    addHerbBtn.setOnClickListener {
                        val intent = Intent(this, DeleteHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addfirstAidBtn.setOnClickListener {
                        val intent = Intent(this, DeleteHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addDiseaseBtn.setOnClickListener {
                        val intent = Intent(this, DeleteHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                    true
                }
                else -> false

            }

        }

    }


}