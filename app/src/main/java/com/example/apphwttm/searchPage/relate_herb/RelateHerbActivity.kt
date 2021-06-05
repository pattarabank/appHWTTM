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

    private var myList: MutableList<String> = ArrayList<String>()
    private var searchListName: MutableList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relate_herb)


        val bundle: Bundle? = intent.extras
        val detail_des: String = bundle!!.getString("disease_des").toString()
        val detail_des_kid: String = bundle!!.getString("disease_des_kid").toString()
        //Log.d("TEST_RELATE_HERB", detail_des.toString())
        //Log.d("TEST_RELATE_HERB", detail_des_kid.toString())
        val strLine = detail_des + "" + detail_des_kid
        strLine.filter { !it.isWhitespace() }.trim()
        //Log.d("TEST_RELATE_HERB", strLine)

        val searchListHerbRecyclerView =
            findViewById<RecyclerView>(R.id.search_RecyclerView_relate_herb)
        searchListHerbRecyclerView.hasFixedSize()
        searchListHerbRecyclerView.layoutManager = LinearLayoutManager(this)
        searchListHerbRecyclerView.adapter = searchListHerbAdapter

        searchInFirestore(strLine)


    }

    private fun searchInFirestore(lineOfDes: String) {


        firebaseFirestore.collection("herb").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    var name = document.getString("name")
                    myList.add(name.toString())
                }
            }
            Log.d("TEST_RELATE_HERB", myList.toString())
            myList.forEach { str ->
                if (lineOfDes.contains(str)) {
                    searchListName.add(str)
                }
            }
            Log.d("TEST_RELATE_HERB", searchListName.toString())
            firebaseFirestore.collection("herb")
                .whereArrayContainsAny("keyword", searchListName)
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