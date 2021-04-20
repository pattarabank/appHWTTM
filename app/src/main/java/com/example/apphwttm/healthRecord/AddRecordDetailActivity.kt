package com.example.apphwttm.healthRecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.apphwttm.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AddRecordDetailActivity : AppCompatActivity() {

    private lateinit var addRecordDetailBtn: ExtendedFloatingActionButton
    private lateinit var editTextAddRecordDetail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record_detail)
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
            Snackbar.make(it, "บันทึกสำเร็จ $userInputText", Snackbar.LENGTH_SHORT).show()
        }


    }
}