package com.example.apphwttm.searchPage.lowerbody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.SearchActivity
import com.example.apphwttm.searchPage.disease.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.NewSearchListDiseaseAdapter
import com.example.apphwttm.searchPage.disease.SearchListDiseaseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DiseaseLowerBodyActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseLowerBody: List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseLowerBodyAdapter =
        NewSearchListDiseaseAdapter(searchListDiseaseLowerBody)

    private lateinit var diseaseLowerBodyBackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_lower_body)

        diseaseLowerBodyBackBtn = findViewById(R.id.myPreviousIconlowerbody)
        diseaseLowerBodyBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //setup recycle view
        val searchListDiseaseLowerBody =
            findViewById<RecyclerView>(R.id.search_RecyclerView_lowerbody)
        searchListDiseaseLowerBody.hasFixedSize()
        searchListDiseaseLowerBody.layoutManager = LinearLayoutManager(this)
        searchListDiseaseLowerBody.adapter = searchListDiseaseLowerBodyAdapter
        searchInFirestore()
    }


    private fun searchInFirestore() {
        firebaseFirestore.collection(" symptom")
            .whereArrayContains("tag", "LOWBODY")
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseLowerBody =
                        it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseLowerBodyAdapter.diseaseSearchModelList =
                        searchListDiseaseLowerBody
                    searchListDiseaseLowerBodyAdapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
    }


    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInAnonymously().addOnCompleteListener() {
                if (!it.isSuccessful) {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }


}