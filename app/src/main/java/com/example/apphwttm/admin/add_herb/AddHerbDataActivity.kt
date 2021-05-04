package com.example.apphwttm.admin.add_herb

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

class AddHerbDataActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    private lateinit var herbName: TextInputLayout
    private lateinit var herbKeyword: TextInputLayout
    private lateinit var herbDes: TextInputLayout
    private lateinit var herbAddBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_herb_data)

        herbName = findViewById(R.id.add_herb_name)
        var name = ""
        herbName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }

        herbKeyword = findViewById(R.id.add_herb_keyword)
        var keywordLine = ""
        herbKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString()
        }

        herbDes = findViewById(R.id.add_herb_des)
        var des = ""
        herbDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString()
        }

        herbAddBtn = findViewById(R.id.herb_add_data_btn)
        herbAddBtn.setOnClickListener {
            addDataToFireStore(name, keywordLine, des)
            Toast.makeText(this,"เพิ่มข้อมูลสมุนไพรใหม่สำเร็จ",Toast.LENGTH_LONG).show()
            finish()
        }

    }

    private fun addDataToFireStore(name: String, keyword: String, des: String) {
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
        firebaseFirestore.collection("testCollection")
            .add(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot written with ID: ${it.id}")
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