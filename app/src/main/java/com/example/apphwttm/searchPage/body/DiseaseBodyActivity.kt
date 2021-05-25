package com.example.apphwttm.searchPage.body

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.SearchActivity
import com.example.apphwttm.data_model.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.NewSearchListDiseaseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DiseaseBodyActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseBody: List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseBodyAdapter = NewSearchListDiseaseAdapter(searchListDiseaseBody)

    private lateinit var diseaseBodyBackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_body)

        diseaseBodyBackBtn = findViewById(R.id.myPreviousIconbody)
        diseaseBodyBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //setup recycle view
        val searchListDiseaseBody = findViewById<RecyclerView>(R.id.search_RecyclerView_body)
        searchListDiseaseBody.hasFixedSize()
        searchListDiseaseBody.layoutManager = LinearLayoutManager(this)
        searchListDiseaseBody.adapter = searchListDiseaseBodyAdapter
        searchInFirestore()
    }

    private fun searchInFirestore() {
        firebaseFirestore.collection(" symptom").orderBy("name", Query.Direction.ASCENDING)
            .whereArrayContains("tag", "BODY")
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseBody = it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseBodyAdapter.diseaseSearchModelList = searchListDiseaseBody
                    searchListDiseaseBodyAdapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
    }


    override fun onStart() {
        super.onStart()
        searchInFirestore()
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInAnonymously().addOnCompleteListener() {
                if (!it.isSuccessful) {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }
}