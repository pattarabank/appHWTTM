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
import com.google.firebase.firestore.Query

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class EditDiseaseDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseEditData: List<DiseaseSearchModel> = ArrayList()
    private val searchListDiseaseAdapter = ManageDataDiseaseAdapter(searchListDiseaseEditData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_disease_data)

        //setup recycle view
        val searchListDiseaseEditRecycler =
            findViewById<RecyclerView>(R.id.edit_disease_data_recyclerview)
        searchListDiseaseEditRecycler.hasFixedSize()
        searchListDiseaseEditRecycler.layoutManager = LinearLayoutManager(this)
        searchListDiseaseEditRecycler.adapter = searchListDiseaseAdapter

        startFirestore()

    }

    private fun startFirestore() {
        firebaseFirestore.collection(" symptom").orderBy("name", Query.Direction.ASCENDING)
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseEditData =
                        it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseAdapter.ManageDiseaseMedelList = searchListDiseaseEditData
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
