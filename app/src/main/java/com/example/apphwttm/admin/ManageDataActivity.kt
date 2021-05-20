package com.example.apphwttm.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.apphwttm.R
import com.example.apphwttm.admin.manage_disease.AddDiseaseDataActivity
import com.example.apphwttm.admin.manage_disease.DeleteDiseaseDataActivity
import com.example.apphwttm.admin.manage_disease.EditDiseaseDataActivity
import com.example.apphwttm.admin.manage_firstAid.AddFirstAidDataActivity
import com.example.apphwttm.admin.manage_firstAid.DeleteFirstAidDataActivity
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
        addHerbBtn.setText(R.string.adminAddHb)
        addfirstAidBtn = findViewById(R.id.add_firstaid)
        addfirstAidBtn.setText(R.string.adminAddFA)
        addDiseaseBtn = findViewById(R.id.add_disease)
        addDiseaseBtn.setText(R.string.adminAddDi)

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
                    addDiseaseBtn.setText(R.string.adminAddDi)
                    addHerbBtn.setText(R.string.adminAddHb)
                    addfirstAidBtn.setText(R.string.adminAddFA)
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
                    addDiseaseBtn.setText(R.string.adminEditDi)
                    addHerbBtn.setText(R.string.adminEditHb)
                    addfirstAidBtn.setText(R.string.adminEditFA)
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
                    addDiseaseBtn.setText(R.string.adminDeleteDi)
                    addHerbBtn.setText(R.string.adminDeleteHb)
                    addfirstAidBtn.setText(R.string.adminDeleteFA)
                    addHerbBtn.setOnClickListener {
                        val intent = Intent(this, DeleteHerbDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addfirstAidBtn.setOnClickListener {
                        val intent = Intent(this, DeleteFirstAidDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }

                    addDiseaseBtn.setOnClickListener {
                        val intent = Intent(this, DeleteDiseaseDataActivity::class.java)
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