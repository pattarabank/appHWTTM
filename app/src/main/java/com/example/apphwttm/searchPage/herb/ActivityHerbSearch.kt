package com.example.apphwttm.searchPage.herb

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class ActivityHerbSearch : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListHerb: List<HerbSearchModel> = ArrayList()
    private val searchListHerbAdapter = SearchListHerbAdapter(searchListHerb)

    private lateinit var herbSearchBackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herb_search)


        herbSearchBackBtn = findViewById(R.id.myPreviousIconHerb)
        herbSearchBackBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        //setup recycle view
        val searchListHerb = findViewById<RecyclerView>(R.id.search_RecyclerView_herb)
        searchListHerb.hasFixedSize()
        searchListHerb.layoutManager = LinearLayoutManager(this)
        searchListHerb.adapter = searchListHerbAdapter
        //search In Firestore()
        findViewById<EditText>(R.id.search_field_Herb).addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val searchText: String =
                    findViewById<EditText>(R.id.search_field_Herb).text.toString()
                searchInFirestore(searchText.toLowerCase())
            }
        })

    }


    private fun searchInFirestore(searchText: String) {
        firebaseFirestore.collection("herb")
            .whereArrayContains("keyword", searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListHerb = it.result!!.toObjects(HerbSearchModel::class.java)
                    searchListHerbAdapter.herbSearchModelList = searchListHerb
                    searchListHerbAdapter.notifyDataSetChanged()
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