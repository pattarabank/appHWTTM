package com.example.apphwttm.admin.manage_herb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.apphwttm.R
import com.example.apphwttm.admin.ManageDataActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditHerbData2Activity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    private lateinit var herbName: TextInputLayout
    private lateinit var herbKeyword: TextInputLayout
    private lateinit var herbDes: TextInputLayout
    private lateinit var herbAddBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_herb_data2)

        val bundle: Bundle? = intent.extras
        val docId: String = bundle!!.getString("send_to_detail_herb_id").toString()
        val itemName: String = bundle!!.getString("send_to_detail_herb_name").toString()
        val itemDes: String = bundle!!.getString("send_to_detail_herb_des").toString()
        val itemKeyword: String =
            bundle!!.getString("send_to_detail_herb_keyword").toString().trim()
        val myItemKeyword =
            itemKeyword.substring(1, itemKeyword.length - 1).trim().filter { !it.isWhitespace() }


//        Log.d("TESTID", docId)
//        Log.d("TESTID", itemName)
//        Log.d("TESTID", itemDes)
//        Log.d("TESTID", myItemKeyword)

        herbName = findViewById(R.id.edit_herb_name)
        herbName.editText?.setText(itemName)
        var name = itemName
        herbName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString().trim()
        }

        herbKeyword = findViewById(R.id.edit_herb_keyword)
        herbKeyword.editText?.setText(myItemKeyword)
        var keywordLine = myItemKeyword
        herbKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString().trim()
        }

        herbDes = findViewById(R.id.edit_herb_des)
        herbDes.editText?.setText(itemDes)
        var des = itemDes
        herbDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString().trim()
        }

        herbAddBtn = findViewById(R.id.herb_edit_data_btn)
        herbAddBtn.setOnClickListener {
            updateDataToFireStore(name, keywordLine, des, docId)
            Toast.makeText(this, "แก้ไขข้อมูลสมุนไพรสำเร็จ", Toast.LENGTH_LONG).show()
            finish()
            val intent = Intent(this, ManageDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

    }

    private fun updateDataToFireStore(name: String, keyword: String, des: String, docId: String) {
        val addName = name
        var addKeyword = keyword.trim().split(",")
        val addDes = des
        //gen name to keyword
        addKeyword = (addKeyword + genKeyword(name))
        //new
        val mySet: Set<String> = addKeyword.toSet()
        addKeyword = mySet.toList()

        val data = hashMapOf(
            "name" to addName,
            "des" to addDes,
            "keyword" to addKeyword
        )
        firebaseFirestore.collection("testCollection").document(docId)
            .update(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot written with ID: $it")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }

    private fun genKeyword(text: String): List<String> {
        var inputStr = text.trim()
        var keyword = mutableListOf<String>()

        val words = inputStr.split("")

        for (word in words) {
            var appendStr = ""
            for (charPosition in inputStr.indices) {
                appendStr += inputStr[charPosition].toString().trim()
                keyword.add(appendStr.trim())
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