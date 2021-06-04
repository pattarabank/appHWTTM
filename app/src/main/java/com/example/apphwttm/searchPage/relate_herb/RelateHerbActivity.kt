package com.example.apphwttm.searchPage.relate_herb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.HerbSearchModel
import com.example.apphwttm.searchPage.herb.SearchListHerbAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class RelateHerbActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListHerb: List<HerbSearchModel> = ArrayList()
    private val searchListHerbAdapter = SearchListHerbAdapter(searchListHerb)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relate_herb)

        val bundle: Bundle? = intent.extras
        val detail_des: String = bundle!!.getString("disease_des").toString()
        val detail_des_kid: String = bundle!!.getString("disease_des_kid").toString()
        Log.d("TEST_RELATE_HERB", detail_des.toString())
        Log.d("TEST_RELATE_HERB", detail_des_kid.toString())


        val searchListHerb = findViewById<RecyclerView>(R.id.search_RecyclerView_relate_herb)
        searchListHerb.hasFixedSize()
        searchListHerb.layoutManager = LinearLayoutManager(this)
        searchListHerb.adapter = searchListHerbAdapter

        searchInFirestore()

    }

    private fun searchInFirestore() {
        firebaseFirestore.collection("herb")
            //.whereArrayContains("keyword", searchText)
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