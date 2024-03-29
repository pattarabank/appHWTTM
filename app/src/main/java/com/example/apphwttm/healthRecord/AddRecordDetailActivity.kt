package com.example.apphwttm.healthRecord

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AddRecordDetailActivity : AppCompatActivity() {

    private lateinit var addRecordDetailBtn: ExtendedFloatingActionButton
    private lateinit var editTextAddRecordDetail: EditText
    private lateinit var addRecordBackBtn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record_detail)

        //set back btn
        addRecordBackBtn = findViewById(R.id.myPreviousIconAddRecordDetail)
        addRecordBackBtn.setOnClickListener {
            onBackPressed()
        }

        var userInputText: String = ""
        editTextAddRecordDetail = findViewById(R.id.editTextTextMultiLine2)
        editTextAddRecordDetail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userInputText = editTextAddRecordDetail.text.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        )



        addRecordDetailBtn = findViewById(R.id.AddRecordDetail_btn)
        addRecordDetailBtn.setOnClickListener {
            saveUserTodayDetailData(userInputText)
            additionalData(userInputText)
            Snackbar.make(it, "บันทึกสำเร็จ", Snackbar.LENGTH_SHORT).show()

        }


    }

    private fun additionalData(str: String) {
        val addedSharedPreferences = getSharedPreferences("additionalData", Context.MODE_PRIVATE)
        val addedEdit = addedSharedPreferences.edit()
        addedEdit.apply {
            putString("ADDITIONALDATA", str)
        }.apply()
    }


    private fun saveUserTodayDetailData(str: String) {
        val dateKey: String = myDateInTH().myDateTodayInTHfun()
        val dataToday = getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
        val currentData: String? = dataToday.getString(dateKey, null)
        var saveData = "+$str"
        val dataTodayWithDetail = getSharedPreferences("USERDATAWITHDETAIL", Context.MODE_PRIVATE)
        val edit = dataTodayWithDetail.edit()
        edit.apply {
            putString(dateKey, "$currentData$saveData")
        }.apply()
        var testMydata: String? = dataTodayWithDetail.getString(dateKey, null)
        Log.d("TESTUSERVALUE", testMydata.toString())
    }


}