package com.example.apphwttm.healthRecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.disease.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.SearchListDiseaseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class RelateDiseaseActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseRelate: List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseRelateAdapter = SearchListDiseaseAdapter(searchListDiseaseRelate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relate_disease)

        val relateData: ArrayList<String> =
            intent.getSerializableExtra("SEND_DATA_TO_RELATE") as ArrayList<String>
        Log.d("RELATE_DATA_TEST", relateData.toString())
        val relateList: List<String> = relateData.toList()

        //setup recycle view
        val searchListDiseaseRelate = findViewById<RecyclerView>(R.id.relateDiseaseRecyclerView)
        searchListDiseaseRelate.hasFixedSize()
        searchListDiseaseRelate.layoutManager = LinearLayoutManager(this)
        searchListDiseaseRelate.adapter = searchListDiseaseRelateAdapter
        searchInFirestore(relateList)


    }

    private fun searchInFirestore(myList: List<String>) {
        firebaseFirestore.collection("testCollection")
            .whereArrayContainsAny("tag", myList)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchListDiseaseRelate = it.result!!.toObjects(DiseaseSearchModel::class.java)
                    searchListDiseaseRelateAdapter.diseaseSearchModelList = searchListDiseaseRelate
                    searchListDiseaseRelateAdapter.notifyDataSetChanged()
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