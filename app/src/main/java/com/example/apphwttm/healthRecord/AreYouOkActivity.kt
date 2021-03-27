package com.example.apphwttm.healthRecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.disease.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.SearchListDiseaseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class AreYouOkActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_are_you_ok)

        //setup recycle view

        //search In Firestore()
        findViewById<EditText>(R.id.search_field_areyouok).addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText: String =
                    findViewById<EditText>(R.id.search_field_areyouok).text.toString()
                searchInFirestore(searchText.toLowerCase())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    private fun searchInFirestore(searchText: String) {
        firebaseFirestore.collection(" symptom")
            .whereArrayContains("keyword", searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    //todo

                } else {
                    Log.d(com.example.apphwttm.healthRecord.TAG, "Error: ${it.exception!!.message}")
                }
            }
    }


    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInAnonymously().addOnCompleteListener() {
                if (!it.isSuccessful) {
                    Log.d(com.example.apphwttm.healthRecord.TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }

}