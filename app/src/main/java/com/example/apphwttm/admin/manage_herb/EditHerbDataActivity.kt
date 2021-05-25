package com.example.apphwttm.admin.manage_herb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.HerbSearchModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class EditHerbDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListHerbEditData: List<HerbSearchModel> = ArrayList()
    private val searchListHerbEditDataAdapter = ManageDataHerbAdapter(searchListHerbEditData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_herb)


        //setup recycle view
        val searchListHerbEditRecycler =
            findViewById<RecyclerView>(R.id.edit_herb_data_recyclerview)
        searchListHerbEditRecycler.hasFixedSize()
        searchListHerbEditRecycler.layoutManager = LinearLayoutManager(this)
        searchListHerbEditRecycler.adapter = searchListHerbEditDataAdapter

        startFirestore()

    }


    private fun startFirestore() {
        firebaseFirestore.collection("herb")
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListHerbEditData = it.result!!.toObjects(HerbSearchModel::class.java)
                    searchListHerbEditDataAdapter.ManageHerbModelList = searchListHerbEditData
                    searchListHerbEditDataAdapter.notifyDataSetChanged()
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