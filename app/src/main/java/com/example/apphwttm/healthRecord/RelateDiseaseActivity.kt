package com.example.apphwttm.healthRecord

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.SearchListDiseaseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class RelateDiseaseActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseRelate: List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseRelateAdapter = SearchListDiseaseAdapter(searchListDiseaseRelate)

    private lateinit var txtViewBottom : TextView

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

        //set text
        txtViewBottom = findViewById(R.id.textViewRelate2)
        val shPIsKid = getSharedPreferences("isKid",Context.MODE_PRIVATE)
        val shPIsKidData = shPIsKid.getBoolean("isKid",true)
        if (shPIsKidData){
            txtViewBottom.text = "แตะที่ชื่ออาการเพื่อดูรายละเอียดสำหรับเด็ก"
        }else{
            txtViewBottom.text = "แตะที่ชื่ออาการเพื่อดูรายละเอียด"
        }
    }

    private fun searchInFirestore(myList: List<String>) {
        firebaseFirestore.collection(" symptom")
            .whereArrayContainsAny("with", myList)
//            .orderBy("name", Query.Direction.ASCENDING)
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