package com.example.apphwttm.admin.manage_firstAid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.FirstAidModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DeleteFirstAidDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListFirstAidDeleteData: List<FirstAidModel> = ArrayList()
    private val searchListFirstAidDeleteDataAdapter =
        DeleteFirstAidAdapter(searchListFirstAidDeleteData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_first_aid_data)

        //setup recycle view
        val searchListFirstAidDeleteRecycler =
            findViewById<RecyclerView>(R.id.delete_firstAid_data_recyclerview)
        searchListFirstAidDeleteRecycler.hasFixedSize()
        searchListFirstAidDeleteRecycler.layoutManager = LinearLayoutManager(this)
        searchListFirstAidDeleteRecycler.adapter = searchListFirstAidDeleteDataAdapter

        startFirestore()
    }

    private fun startFirestore() {
        firebaseFirestore.collection("first aid").orderBy("name", Query.Direction.ASCENDING)
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListFirstAidDeleteData = it.result!!.toObjects(FirstAidModel::class.java)
                    searchListFirstAidDeleteDataAdapter.DeleteFirstAidModelList =
                        searchListFirstAidDeleteData
                    searchListFirstAidDeleteDataAdapter.notifyDataSetChanged()
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