package com.example.apphwttm.searchPage.firstaid

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
import com.example.apphwttm.R
import com.example.apphwttm.SearchActivity
import com.example.apphwttm.searchPage.herb.HerbSearchModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class ActivityFirstAidSearch : AppCompatActivity() {


    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListFirstAid: List<FirstAidModel> = ArrayList()
    private val searchListFirstAidAdapter = SearchListFirstAidAdapter(searchListFirstAid)

    private lateinit var firstaidSearchBackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid_search)
        startFirestore()

        firstaidSearchBackBtn = findViewById(R.id.myPreviousIconFirstaid)
        firstaidSearchBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //setup recycle view
        val searchListDisease = findViewById<RecyclerView>(R.id.myDisease_recylerview_firstaid)
        searchListDisease.hasFixedSize()
        searchListDisease.layoutManager = LinearLayoutManager(this)
        searchListDisease.adapter = searchListFirstAidAdapter
        //search In Firestore()
        findViewById<EditText>(R.id.search_field_firstaid).addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val searchText: String =
                    findViewById<EditText>(R.id.search_field_firstaid).text.toString()
                if(searchText == ""){
                    startFirestore()
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText: String =
                    findViewById<EditText>(R.id.search_field_firstaid).text.toString()
                searchInFirestore(searchText.toLowerCase())
            }
        })

    }


    private fun searchInFirestore(searchText: String) {
        firebaseFirestore.collection("first aid")
            .whereArrayContains("keyword", searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListFirstAid = it.result!!.toObjects(FirstAidModel::class.java)
                    searchListFirstAidAdapter.firstAidModelList = searchListFirstAid
                    searchListFirstAidAdapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
    }

    private fun startFirestore() {
        firebaseFirestore.collection("first aid")
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListFirstAid = it.result!!.toObjects(FirstAidModel::class.java)
                    searchListFirstAidAdapter.firstAidModelList = searchListFirstAid
                    searchListFirstAidAdapter.notifyDataSetChanged()
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