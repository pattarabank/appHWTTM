package com.example.apphwttm.admin.manage_disease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.apphwttm.R
import com.example.apphwttm.admin.ManageDataActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditDiseaseData2Activity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    private lateinit var diseaseName: TextInputLayout
    private lateinit var diseaseTag: TextInputLayout
    private lateinit var diseaseKeyword: TextInputLayout
    private lateinit var diseaseDes: TextInputLayout
    private lateinit var diseaseDesKid: TextInputLayout
    private lateinit var diseaseWith: TextInputLayout
    private lateinit var diseaseAddBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_disease_data2)

        val bundle: Bundle? = intent.extras
        val docId: String = bundle!!.getString("send_to_detail_disease_id").toString()
        val itemName: String = bundle!!.getString("send_to_detail_disease_name").toString()
        val itemDes: String = bundle!!.getString("send_to_detail_disease_des").toString()
        val itemDes_kid: String = bundle!!.getString("send_to_detail_disease_des_kid").toString()
        val itemKeyword: String = bundle!!.getString("send_to_detail_disease_keyword").toString()
        val itemWith: String = bundle!!.getString("send_to_detail_disease_with").toString()
        val myItemKeyword: String =
            itemKeyword.substring(1, itemKeyword.length - 1).filter { !it.isWhitespace() }

        val myItemWith: String =
            itemWith.substring(1, itemWith.length - 1).filter { !it.isWhitespace() }




        diseaseName = findViewById(R.id.edit_disease_name)
        diseaseName.editText?.setText(itemName)
        var name = itemName
        diseaseName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }

        diseaseTag = findViewById(R.id.edit_disease_add_tag)
        val tagItems = listOf<String>("ศีรษะ", "ลำตัว", "ลำตัวส่วนล่าง")
        val adapter = ArrayAdapter(this, R.layout.add_disease_dropdown_items, tagItems)
        (diseaseTag.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        var tag = ""
        diseaseTag.editText?.doOnTextChanged { text, start, before, count ->
            tag = text.toString()
        }

        diseaseKeyword = findViewById(R.id.edit_disease_keyword)
        diseaseKeyword.editText?.setText(myItemKeyword)
        var keywordLine = myItemKeyword
        diseaseKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString()
        }

        diseaseDes = findViewById(R.id.edit_disease_des)
        diseaseDes.editText?.setText(itemDes)
        var des = itemDes
        diseaseDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString()
        }

        diseaseDesKid = findViewById(R.id.edit_disease_des_kid)
        diseaseDesKid.editText?.setText(itemDes_kid)
        var desKid = itemDes_kid
        diseaseDesKid.editText?.doOnTextChanged { text, start, before, count ->
            desKid = text.toString()
        }

        diseaseWith = findViewById(R.id.edit_disease_with)
        diseaseWith.editText?.setText(myItemWith)
        var with = myItemWith
        diseaseWith.editText?.doOnTextChanged { text, start, before, count ->
            with = text.toString()
        }

        diseaseAddBtn = findViewById(R.id.edit_disease_btn)
        diseaseAddBtn.setOnClickListener {
            updateDataToFireStore(name, tag, keywordLine, des, desKid, docId, with)
            Toast.makeText(this, "แก้ไขข้อมูลโรคสำเร็จ", Toast.LENGTH_LONG).show()
            finish()
            val intent = Intent(this, ManageDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

    }

    private fun updateDataToFireStore(
        name: String,
        tag: String,
        keyword: String,
        des: String,
        des_kid: String,
        docId: String,
        with: String
    ) {
        var addName = name
        var addKeyword = keyword.trim().split(",")
        var addDes = des
        var addDesKid = des_kid
        var addWith = listOf<String>("")
        if (with != "") {
            addWith = with.split(",")
        }
        //gen name to keyword
        addKeyword = addKeyword + genKeyword(name)
        //get tag
        var addTag: List<String> = listOf()
        when (tag) {
            "ศีรษะ" -> addTag = listOf("HEAD")
            "ลำตัว" -> addTag = listOf("BODY")
            "ลำตัวส่วนล่าง" -> addTag = listOf("LOWBODY")
            "" -> addTag = listOf("BODY")
        }
        //check des kid is empty
        if (des_kid == "") {
            addDesKid = des
        }
        val data = hashMapOf(
            "name" to addName,
            "des" to addDes,
            "des_kid" to addDesKid,
            "keyword" to addKeyword,
            "tag" to addTag,
            "with" to addWith
        )
        firebaseFirestore.collection(" symptom").document(docId)
            //firebaseFirestore.collection("testCollection").document(docId)
            .update(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot written with ID: $it")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

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