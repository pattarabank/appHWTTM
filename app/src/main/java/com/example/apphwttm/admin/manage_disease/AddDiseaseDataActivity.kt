package com.example.apphwttm.admin.manage_disease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.apphwttm.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddDiseaseDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    private lateinit var diseaseName: TextInputLayout
    private lateinit var diseaseTag: TextInputLayout
    private lateinit var diseaseKeyword: TextInputLayout
    private lateinit var diseaseDes: TextInputLayout
    private lateinit var diseaseDesKid: TextInputLayout
    private lateinit var diseaseAddBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_disease_data)

        diseaseName = findViewById(R.id.add_disease_name)
        var name = ""
        diseaseName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }

        diseaseTag = findViewById(R.id.add_disease_add_tag)
        val tagItems = listOf<String>("ศีรษะ", "ลำตัว", "ลำตัวส่วนล่าง")
        val adapter = ArrayAdapter(this, R.layout.add_disease_dropdown_items, tagItems)
        (diseaseTag.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        var tag = ""
        diseaseTag.editText?.doOnTextChanged { text, start, before, count ->
            tag = text.toString()
        }

        diseaseKeyword = findViewById(R.id.add_disease_keyword)
        var keywordLine = ""
        diseaseKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString()
        }

        diseaseDes = findViewById(R.id.add_disease_des)
        var des = ""
        diseaseDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString()
        }

        diseaseDesKid = findViewById(R.id.add_disease_des_kid)
        var desKid = ""
        diseaseDesKid.editText?.doOnTextChanged { text, start, before, count ->
            desKid = text.toString()
        }

        diseaseAddBtn = findViewById(R.id.add_disease_btn)
        diseaseAddBtn.setOnClickListener {
            addDataToFireStore(name,tag, keywordLine, des,desKid)
            Toast.makeText(this, "เพิ่มข้อมูลโรคใหม่สำเร็จ", Toast.LENGTH_LONG).show()
            finish()
        }


    }

    private fun addDataToFireStore(
        name: String,
        tag: String,
        keyword: String,
        des: String,
        des_kid: String
    ) {
        var addName = name
        var addKeyword = keyword.trim().split(",")
        var addDes = des
        var addDesKid = des_kid
        //gen name to keyword
        addKeyword = addKeyword + genKeyword(name)
        //get tag
        var addTag: List<String> = listOf()
        when (tag) {
            "ศีรษะ" -> addTag = listOf("HEAD")
            "ลำตัว" -> addTag = listOf("BODY")
            "ลำตัวส่วนล่าง" -> addTag = listOf("LOWBODY")
        }
        //check des kid is empty
        if (des_kid == ""){
            addDesKid = des
        }
        val data = hashMapOf(
            "name" to addName,
            "des" to addDes,
            "des_kid" to addDesKid,
            "keyword" to addKeyword,
            "tag" to addTag
        )
        firebaseFirestore.collection("testCollection")
            .add(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot written with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

//        Log.d("TESTADMIN",addName+"1")
//        Log.d("TESTADMIN",addDes+"2")
//        Log.d("TESTADMIN",addDesKid+"3")
//        Log.d("TESTADMIN",addKeyword.toString()+"4")
//        Log.d("TESTADMIN",addTag.toString()+"5")

    }

    private fun genKeyword(text: String): List<String> {
        var inputStr = text
        var keyword = mutableListOf<String>()

        val words = inputStr.split("")

        for (word in words) {
            var appendStr = ""
            for (charPosition in inputStr.indices) {
                appendStr += inputStr[charPosition].toString()
                keyword.add(appendStr)
            }
        }
        var temp: Set<String> = keyword.toSet()
        keyword = temp.toMutableList()
        return keyword
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