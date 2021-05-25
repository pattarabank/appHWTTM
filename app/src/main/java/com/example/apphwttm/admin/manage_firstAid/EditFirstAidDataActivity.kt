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

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class EditFirstAidDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListFirstAidEditData: List<FirstAidModel> = ArrayList()
    private val searchListFirstAidAdapter = ManageDataFirstAidAdapter(searchListFirstAidEditData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_first_aid_data)

        //setup recycle view
        val searchListFirstAidEditRecycler =
            findViewById<RecyclerView>(R.id.edit_firstAid_data_recyclerview)
        searchListFirstAidEditRecycler.hasFixedSize()
        searchListFirstAidEditRecycler.layoutManager = LinearLayoutManager(this)
        searchListFirstAidEditRecycler.adapter = searchListFirstAidAdapter

        startFirestore()

    }

    private fun startFirestore() {
        firebaseFirestore.collection("first aid")
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListFirstAidEditData = it.result!!.toObjects(FirstAidModel::class.java)
                    searchListFirstAidAdapter.ManageFirstAidModelList = searchListFirstAidEditData
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