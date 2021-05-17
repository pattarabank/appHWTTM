package com.example.apphwttm.searchPage.head

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

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DiseaseHeadActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseHead : List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseHeadAdapter = NewSearchListDiseaseAdapter(searchListDiseaseHead)

    private lateinit var diseaseHeadBackBtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_head)


        diseaseHeadBackBtn = findViewById(R.id.myPreviousIconhead)
        diseaseHeadBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //setup recycle view
        val searchListDiseaseHead = findViewById<RecyclerView>(R.id.search_RecyclerView_head)
        searchListDiseaseHead.hasFixedSize()
        searchListDiseaseHead.layoutManager = LinearLayoutManager(this)
        searchListDiseaseHead.adapter = searchListDiseaseHeadAdapter
        searchInFirestore()
    }

    private fun searchInFirestore() {
        firebaseFirestore.collection(" symptom")
            .whereArrayContains("tag", "HEAD")
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseHead = it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseHeadAdapter.diseaseSearchModelList = searchListDiseaseHead
                    searchListDiseaseHeadAdapter.notifyDataSetChanged()
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