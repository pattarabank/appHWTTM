package com.example.apphwttm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

//    val sharedPreferencesName = getSharedPreferences("sharedPrefsName", Context.MODE_PRIVATE)
//    val editorName = sharedPreferencesName.edit()
//    editorName.apply {
//        putString("sharedPrefsName", name)
//    }.apply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val outlinedButton: Button = findViewById(R.id.outlinedButton)
        val nameTextField: TextInputLayout = findViewById(R.id.nameTextField)
        val birthDateTextField: TextInputLayout = findViewById(R.id.birthDateTextField)
        val diseaseTextField: TextInputLayout = findViewById(R.id.diseaseTextField)

        outlinedButton.setOnClickListener {
            var name = nameTextField.editText?.text.toString()
            var bDate = birthDateTextField.editText?.text.toString()
            var disease = diseaseTextField.editText?.text.toString()
            //Snackbar.make(it, "$name $bDate $disease", Snackbar.LENGTH_SHORT).show()
        }


    }
}