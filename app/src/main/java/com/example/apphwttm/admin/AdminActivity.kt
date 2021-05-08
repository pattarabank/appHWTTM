package com.example.apphwttm.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import com.example.apphwttm.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class AdminActivity : AppCompatActivity() {

    private lateinit var TextFieldId: TextInputLayout
    private lateinit var TextFieldPass: TextInputLayout
    private lateinit var LoginBtn: Button
    private val ADMINID: String = "admin"
    private val ADMINPASSWORD: String = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        TextFieldId = findViewById(R.id.admin_id)
        var tempId = ""
        TextFieldId.editText?.doOnTextChanged { text, start, before, count ->
            tempId = text.toString()
        }

        TextFieldPass = findViewById(R.id.admin_pass)
        var tempPass = ""
        TextFieldPass.editText?.doOnTextChanged { text, start, before, count ->
            tempPass = text.toString()
        }

        LoginBtn = findViewById(R.id.admin_login_btn)
        LoginBtn.setOnClickListener {
            Log.d("TESTADMIN", "$tempId $tempPass")
            if (tempId == ADMINID && tempPass == ADMINPASSWORD) {
                val intent = Intent(this, ManageDataActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }else{
                Snackbar.make(it,"Id หรือ Password ผิด กรุณากรอกใหม่",Snackbar.LENGTH_SHORT).show()
            }
        }


    }
}