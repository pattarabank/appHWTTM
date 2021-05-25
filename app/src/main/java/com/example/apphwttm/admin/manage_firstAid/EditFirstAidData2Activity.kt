package com.example.apphwttm.admin.manage_firstAid

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

        val bundle: Bundle? = intent.extras
        val docId: String = bundle!!.getString("send_to_detail_firstaid_id").toString()
        val itemName: String = bundle!!.getString("send_to_detail_firstaid_name").toString()
        val itemDes: String = bundle!!.getString("send_to_detail_firstaid_des").toString()
        val itemKeyword: String =
            bundle!!.getString("send_to_detail_firstaid_keyword").toString().trim()
        val myItemKeyword =
            itemKeyword.substring(1, itemKeyword.length - 1).trim().filter { !it.isWhitespace() }



        firstAidName = findViewById(R.id.edit_firstAid_name)
        firstAidName.editText?.setText(itemName)
        var name = itemName
        firstAidName.editText?.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }

        firstAidKeyword = findViewById(R.id.edit_firstAid_keyword)
        firstAidKeyword.editText?.setText(myItemKeyword)
        var keywordLine = myItemKeyword
        firstAidKeyword.editText?.doOnTextChanged { text, start, before, count ->
            keywordLine = text.toString()
        }

        firstAidDes = findViewById(R.id.edit_firstAid_des)
        firstAidDes.editText?.setText(itemDes)
        var des = itemDes
        firstAidDes.editText?.doOnTextChanged { text, start, before, count ->
            des = text.toString()
        }

        firstAidAddBtn = findViewById(R.id.firstAid_edit_data_btn)
        firstAidAddBtn.setOnClickListener {
            updateDataToFireStore(name, keywordLine, des, docId)
            Toast.makeText(this, "แก้ไขข้อมูลการปฐมพยาบาลเบื้องต้นสำเร็จ", Toast.LENGTH_LONG).show()
            finish()
            val intent = Intent(this, ManageDataActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

    }

    private fun updateDataToFireStore(name: String, keyword: String, des: String, docId: String) {
        var addName = name
        var addKeyword = keyword.trim().split(",")
        var addDes = des
        //gen name to keyword
        addKeyword = addKeyword + genKeyword(name)

        val mySet: Set<String> = addKeyword.toSet()
        addKeyword = mySet.toList()

        val data = hashMapOf(
            "name" to addName,
            "des" to addDes,
            "keyword" to addKeyword
        )
        firebaseFirestore.collection("first aid").document(docId)
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