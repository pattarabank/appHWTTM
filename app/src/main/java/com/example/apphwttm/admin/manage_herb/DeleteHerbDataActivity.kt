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
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DeleteHerbDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListHerbDeleteData: List<HerbSearchModel> = ArrayList()
    private val searchListHerbDeleteDataAdapter = DeleteHerbAdapter(searchListHerbDeleteData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_herb)

        //setup recycle view
        val searchListHerbDeleteRecycler =
            findViewById<RecyclerView>(R.id.delete_herb_data_recyclerview)
        searchListHerbDeleteRecycler.hasFixedSize()
        searchListHerbDeleteRecycler.layoutManager = LinearLayoutManager(this)
        searchListHerbDeleteRecycler.adapter = searchListHerbDeleteDataAdapter

        startFirestore()
    }

    private fun startFirestore() {
        firebaseFirestore.collection("herb").orderBy("name", Query.Direction.ASCENDING)
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListHerbDeleteData = it.result!!.toObjects(HerbSearchModel::class.java)
                    searchListHerbDeleteDataAdapter.DeleteHerbModelList = searchListHerbDeleteData
                    searchListHerbDeleteDataAdapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
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