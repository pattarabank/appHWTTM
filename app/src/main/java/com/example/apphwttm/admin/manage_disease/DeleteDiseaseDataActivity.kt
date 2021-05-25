package com.example.apphwttm.admin.manage_disease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.DiseaseSearchModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class DeleteDiseaseDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseDeleteData: List<DiseaseSearchModel> = ArrayList()
    private val searchListDiseaseDeleteDataAdapter =
        DeleteDiseaseAdapter(searchListDiseaseDeleteData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_disease_data)

        //setup recycle view
        val searchListDiseaseDeleteRecycler =
            findViewById<RecyclerView>(R.id.delete_disease_data_recyclerview)
        searchListDiseaseDeleteRecycler.hasFixedSize()
        searchListDiseaseDeleteRecycler.layoutManager = LinearLayoutManager(this)
        searchListDiseaseDeleteRecycler.adapter = searchListDiseaseDeleteDataAdapter

        startFirestore()

    }

    private fun startFirestore() {
        firebaseFirestore.collection(" symptom")
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseDeleteData =
                        it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseDeleteDataAdapter.DeleteDiseaseModelList =
                        searchListDiseaseDeleteData
                    searchListDiseaseDeleteDataAdapter.notifyDataSetChanged()
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