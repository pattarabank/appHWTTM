package com.example.apphwttm.healthRecord

import android.content.Context
import android.content.Intent
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
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class RelateDiseaseActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchListDiseaseRelate: List<DiseaseSearchModel> = ArrayList()
    private var searchListDiseaseRelateAdapter = SearchListDiseaseAdapter(searchListDiseaseRelate)

    private lateinit var txtViewBottom: TextView

    //private lateinit var txtViewTestResponse: TextView
    private lateinit var txtViewRelateBackBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relate_disease)

        //set back btn
        txtViewRelateBackBtn = findViewById(R.id.myPreviousIconRelateDisease)
        txtViewRelateBackBtn.setOnClickListener {
            onBackPressed()
        }

        val relateData: ArrayList<String> =
            intent.getSerializableExtra("SEND_DATA_TO_RELATE") as ArrayList<String>
        //Log.d("RELATE_DATA_TEST", relateData.toString())
        var relateList: List<String> = relateData.toList()


        Log.d("TESTOKHTTP", relateList.toString() + "list เดิม")
        //test ok http

        //txtViewTestResponse = findViewById(R.id.my_test_txt_response)
        val addedSharedPreferences = getSharedPreferences("additionalData", Context.MODE_PRIVATE)
        val addedData: String = addedSharedPreferences.getString("ADDITIONALDATA", null).toString()
        //getAddedDataFromFlask(addedData)


        //Log.d("TESTOKHTTP", addedData.toString())

        //setup recycle view
        val searchListDiseaseRelate = findViewById<RecyclerView>(R.id.relateDiseaseRecyclerView)
        searchListDiseaseRelate.hasFixedSize()
        searchListDiseaseRelate.layoutManager = LinearLayoutManager(this)
        searchListDiseaseRelate.adapter = searchListDiseaseRelateAdapter
        searchInFirestore(relateList)

        //set text
        txtViewBottom = findViewById(R.id.textViewRelate2)
        val shPIsKid = getSharedPreferences("isKid", Context.MODE_PRIVATE)
        val shPIsKidData = shPIsKid.getBoolean("isKid", true)
        if (shPIsKidData) {
            txtViewBottom.text = "แตะที่ชื่ออาการเพื่อดูรายละเอียดสำหรับเด็ก"
        } else {
            txtViewBottom.text = "แตะที่ชื่ออาการเพื่อดูรายละเอียด"
        }
        ///////////////////

        try {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("value", addedData)
                .build()
            val request = Request.Builder()
                .url("http://192.168.1.34:5000/post")
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            Log.d("TESTOKHTTP", relateList.toString() + "Can't Connect")
                            searchInFirestore(relateList)
                        }
                    })
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            //txtViewTestResponse.text = response.body!!.string()
                            val myLine =
                                response.body!!.string().filter { !it.isWhitespace() }.split(",")
                            //Log.d("TESTOKHTTP", newListData.toString() + "EMPTY")
                            if (myLine.toString() == "[]") {
                                Log.d("TESTOKHTTP", myLine.toString() + "EMPTY")
                                Log.d("TESTOKHTTP", relateList.toString() + "EMPTY")
                                searchInFirestore(relateList)
                            } else {
                                var newListData: MutableList<String> = ArrayList()
                                myLine.forEach {
                                    //Log.d("TESTOKHTTP", it.substring(1, it.length - 1) + "")
                                    newListData.add(it.substring(1, it.length - 1))
                                }
                                Log.d("TESTOKHTTP", (relateList + newListData).toString())
                                searchInFirestore(relateList + newListData)
                            }

                        }

                    })


                }

            })
        }catch (e:Exception){
            searchInFirestore(relateList)
        }
//        val client = OkHttpClient()
//        val formBody = FormBody.Builder()
//            .add("value", addedData)
//            .build()
//        val request = Request.Builder()
//            .url("http://192.168.1.34:5000/post")
//            .post(formBody)
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                runOnUiThread(object : Runnable {
//                    override fun run() {
//                        Log.d("TESTOKHTTP", relateList.toString() + "Can't Connect")
//                        searchInFirestore(relateList)
//                    }
//                })
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (!response.isSuccessful) throw IOException("Unexpected code $response")
//                runOnUiThread(object : Runnable {
//                    override fun run() {
//                        //txtViewTestResponse.text = response.body!!.string()
//                        val myLine =
//                            response.body!!.string().filter { !it.isWhitespace() }.split(",")
//                        //Log.d("TESTOKHTTP", newListData.toString() + "EMPTY")
//                        if (myLine.toString() == "[]") {
//                            Log.d("TESTOKHTTP", myLine.toString() + "EMPTY")
//                            Log.d("TESTOKHTTP", relateList.toString() + "EMPTY")
//                            searchInFirestore(relateList)
//                        } else {
//                            var newListData: MutableList<String> = ArrayList()
//                            myLine.forEach {
//                                //Log.d("TESTOKHTTP", it.substring(1, it.length - 1) + "")
//                                newListData.add(it.substring(1, it.length - 1))
//                            }
//                            Log.d("TESTOKHTTP", (relateList + newListData).toString())
//                            searchInFirestore(relateList + newListData)
//                        }
//
//                    }
//
//                })
//
//
//            }
//
//        })


//        val resetData = addedSharedPreferences.edit()
//        resetData.apply {
//            putString("ADDITIONALDATA", "")
//        }.apply()

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

    //example Asynchronous Get
    //private fun getAddedDataFromFlask(str: String) {
//        val client = OkHttpClient()
//        val formBody = FormBody.Builder()
//            .add("value", str)
//            .build()
//        val request = Request.Builder()
//            .url("http://192.168.1.34:5000/post")
//            .post(formBody)
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                runOnUiThread(object : Runnable {
//                    override fun run() {
//                        Log.d("TESTOKHTTP", "Can't Connect")
//                    }
//                })
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (!response.isSuccessful) throw IOException("Unexpected code $response")
//                runOnUiThread(object : Runnable {
//                    override fun run() {
//                        //txtViewTestResponse.text = response.body!!.string()
//                        val myLine =
//                            response.body!!.string().filter { !it.isWhitespace() }.split(",")
//                        if (myLine.toString() == "[]") {
//                            Log.d("TESTOKHTTP", myLine.toString() + "EMPTY")
//                        } else {
//                            Log.d("TESTOKHTTP", myLine.toString())
//                        }
//
//                    }
//
//                })
//
//
//            }
//
//        })


    //}


}

