package com.example.apphwttm.admin.manage_firstAid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.apphwttm.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditFirstAidData2Activity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    private lateinit var firstAidName: TextInputLayout
    private lateinit var firstAidKeyword: TextInputLayout
    private lateinit var firstAidDes: TextInputLayout
    private lateinit var firstAidAddBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_first_aid_data2)

        var bundle: Bundle? = intent.extras
        var docId: String = bundle!!.getString("send_to_detail_firstaid_id").toString()

        firstAidName = findViewById(R.id.edit_firstAid_name)
        var name = ""
        firstAidName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }

        firstAidKeyword = findViewById(R.id.edit_firstAid_keyword)
        var keywordLine = ""
        firstAidKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString()
        }

        firstAidDes = findViewById(R.id.edit_firstAid_des)
        var des = ""
        firstAidDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString()
        }

        firstAidAddBtn = findViewById(R.id.firstAid_edit_data_btn)
        firstAidAddBtn.setOnClickListener {
            addDataToFireStore(name, keywordLine, des,docId)
            Toast.makeText(this,"แก้ไขข้อมูลการปฐมพยาบาลเบื้องต้นสำเร็จ", Toast.LENGTH_LONG).show()
            finish()
        }

    }

    private fun addDataToFireStore(name: String, keyword: String, des: String,docId:String) {
        var addName = name
        var addKeyword = keyword.trim().split(",")
        var addDes = des
        //gen name to keyword
        addKeyword = addKeyword + genKeyword(name)

        val data = hashMapOf(
            "name" to addName,
            "des" to addDes,
            "keyword" to addKeyword
        )
        firebaseFirestore.collection("testCollection").document(docId)
            .set(data)
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