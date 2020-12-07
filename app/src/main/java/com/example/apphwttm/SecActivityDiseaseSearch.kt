package com.example.apphwttm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class SecActivityDiseaseSearch : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDisease: List<DiseaseSearchModel> = ArrayList()
    private val searchListDiseaseAdapter = SearchListDiseaseAdapter(searchListDisease)


    private lateinit var diseaseSearchBackBtn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec_disease_search)


        diseaseSearchBackBtn = findViewById(R.id.myPreviousIcon)
        diseaseSearchBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        //setup recycle view
        val searchListDisease = findViewById<RecyclerView>(R.id.myDisease_recylerview)
        searchListDisease.hasFixedSize()
        searchListDisease.layoutManager = LinearLayoutManager(this)
        searchListDisease.adapter = searchListDiseaseAdapter
        //search In Firestore()
        findViewById<EditText>(R.id.search_field).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText: String = findViewById<EditText>(R.id.search_field).text.toString()
                searchInFirestore(searchText.toLowerCase())
            }
        })


    }

    private fun searchInFirestore(searchText: String) {
        firebaseFirestore.collection("โรค")
            .whereArrayContains("search_keywords", searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDisease = it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseAdapter.diseaseSearchModelList = searchListDisease
                    searchListDiseaseAdapter.notifyDataSetChanged()
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