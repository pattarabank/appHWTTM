package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.apphwttm.healthRecord.AreYouOkModel
import com.example.apphwttm.healthRecord.HealthCareActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class HomeActivity : AppCompatActivity() {//หน้าแรก

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private lateinit var bottomBar: BottomNavigationView
    private lateinit var iconSmile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.selectedItemId = R.id.itemHome
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
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

        ////////

        iconSmile = findViewById(R.id.amfine_icon)
        iconSmile.setOnClickListener {
            val intent = Intent(this, HealthCareActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


    }
    //pre load data in firestore
    private fun startFirestore() {
        firebaseFirestore.collection(" symptom").orderBy("name", Query.Direction.ASCENDING)
            //.whereArrayContains("search_keywords",searchText)
            .get()
    }

    override fun onStart() {
        super.onStart()
        startFirestore()
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInAnonymously().addOnCompleteListener() {
                if (!it.isSuccessful) {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }
}