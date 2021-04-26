package com.example.apphwttm.healthRecord

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH
import com.example.apphwttm.searchPage.disease.DiseaseSearchModel
import com.example.apphwttm.searchPage.disease.SearchListDiseaseAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "FIRESTORE_SEARCH_LOG"

class AreYouOkActivity : AppCompatActivity(), OnItemClickListener {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var searchList: List<AreYouOkModel> = ArrayList()
    private val searchListAreYouOkAdapter = AreYouOkAdapter(searchList, this)

    private lateinit var myChipGroup: ChipGroup
    private lateinit var backIconAreyouok: TextView
    private var chipArrayList = ArrayList<String>()
    private lateinit var areYouOkBtn: ExtendedFloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_are_you_ok)



        startFirestore()
//        var temp : String = myDateInTH().myDateInTHfun()
//        Log.d("TESTDATE",temp)
        myChipGroup = findViewById(R.id.areyouokChipgroup)
        backIconAreyouok = findViewById(R.id.myPreviousIconAreyouok)
        backIconAreyouok.setOnClickListener {
            val intent = Intent(this, HealthCareActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        //setup recycle view
        val search_list = findViewById<RecyclerView>(R.id.areyouokRecycler)
        search_list.hasFixedSize()
        search_list.layoutManager = LinearLayoutManager(this)
        search_list.adapter = searchListAreYouOkAdapter
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
                val searchText: String =
                    findViewById<EditText>(R.id.search_field_areyouok).text.toString()
                if (searchText == "") {
                    startFirestore()
                }
            }
        })
        areYouOkBtn = findViewById(R.id.button_areyouok)
        areYouOkBtn.setOnClickListener {
            if (chipArrayList.size < 1 || chipArrayList.size > 3) {
                Snackbar.make(it, "กรุณาเลือกอาการ 1 - 3 อาการ", Snackbar.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, AreYouOk2Activity::class.java)
                intent.putExtra("are_you_ok_data", chipArrayList)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
            //save data
            val userHealthCareLimitPerDay =
                getSharedPreferences("userLimitPerDay", Context.MODE_PRIVATE)
            val isOk = userHealthCareLimitPerDay.getString("userLimitPerDay", null)
            if (isOk == null) {
                saveUserTodayData()
            } else {
                Snackbar.make(it, "วันนี้บันทึกไปแล้ว", Snackbar.LENGTH_SHORT).show()
            }

        }

    }

    private fun saveUserTodayData() {
        //key
        val dateKey: String = myDateInTH().myDateInTHfun()
        //value แยกแต่ละโรคด้วย space
        var userValue: String = ""
        chipArrayList.forEach { str ->
            userValue += "$str-"
        }
        //shP
        val dataToday = getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
        val editDataToday = dataToday.edit()
        editDataToday.apply {
            putString(dateKey, userValue)
        }.apply()
        var testMydata: String? = dataToday.getString(dateKey, null)
        Log.d("TESTUSERVALUE", testMydata.toString())
    }


    private fun searchInFirestore(searchText: String) {
        firebaseFirestore.collection(" symptom")
            .whereArrayContains("keyword", searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchList = it.result!!.toObjects(AreYouOkModel::class.java)
                    searchListAreYouOkAdapter.searchList = searchList
                    searchListAreYouOkAdapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
    }

    private fun startFirestore() {
        firebaseFirestore.collection(" symptom")
            //.whereArrayContains("search_keywords",searchText)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    searchList = it.result!!.toObjects(AreYouOkModel::class.java)
                    searchListAreYouOkAdapter.searchList = searchList
                    searchListAreYouOkAdapter.notifyDataSetChanged()
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

    private fun createChip(name: String) {
        val inflater = LayoutInflater.from(this@AreYouOkActivity)
        val tempChip = inflater.inflate(R.layout.single_item_chip, null, false) as Chip
        tempChip.text = name
        tempChip.setOnCloseIconClickListener { view ->
            chipArrayList.remove(tempChip.text.toString())
            myChipGroup.removeView(view)
        }
        var count = 0;
        if (myChipGroup.isEmpty()) {
            chipArrayList.add(tempChip.text.toString())
            myChipGroup.addView(tempChip)
        } else {
            for (i in chipArrayList) {
                if (i == tempChip.text.toString()) {
                    count++;
                }
            }
            if (count == 0) {
                chipArrayList.add(tempChip.text.toString())
                myChipGroup.addView(tempChip)
            } else {
                Toast.makeText(this, "คุณเลือกโรคซ้ำ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(position: Int) {
        createChip(searchList[position].name)
    }

}