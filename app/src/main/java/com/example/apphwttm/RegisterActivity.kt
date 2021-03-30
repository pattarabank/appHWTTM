package com.example.apphwttm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.get
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.util.*

class RegisterActivity : AppCompatActivity() {

    //    val sharedPreferencesName = getSharedPreferences("sharedPrefsName", Context.MODE_PRIVATE)
//    val editorName = sharedPreferencesName.edit()
//    editorName.apply {
//        putString("sharedPrefsName", name)
//    }.apply()

    private lateinit var regisYearBtn: ExtendedFloatingActionButton
    private lateinit var myCalendar: TextView
    private lateinit var user_year_txt : TextView
    private lateinit var txt_user_name : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        myCalendar = findViewById(R.id.regis_txt_calendar)
        myCalendar.setOnClickListener {
            btnYear()
        }
        regisYearBtn = findViewById(R.id.extended_fab)
        user_year_txt = findViewById(R.id.user_year_txt)
        txt_user_name = findViewById(R.id.user_name_input_txt)
        regisYearBtn.setOnClickListener {
            //save data
            var name = txt_user_name.text.toString()
            //Log.d("TESTREGIS",name)
            val sharedPreferencesName = getSharedPreferences("userName", Context.MODE_PRIVATE)
            val editorName = sharedPreferencesName.edit()
            editorName.apply{
                putString("userName",name)
            }.apply()

            var year = user_year_txt.text.toString()
            val sharedPreferencesYear = getSharedPreferences("userYear",Context.MODE_PRIVATE)
            val editorYear = sharedPreferencesYear.edit()
            editorYear.apply{
                putString("userYear",year)
            }.apply()

            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

//        val outlinedButton: Button = findViewById(R.id.outlinedButton)
//        val nameTextField: TextInputLayout = findViewById(R.id.nameTextField)
//        val birthDateTextField: TextInputLayout = findViewById(R.id.birthDateTextField)
//
//        outlinedButton.setOnClickListener {
//            var name = nameTextField.editText?.text.toString()
//            val sharedPreferencesName = getSharedPreferences("sharedPrefsName", Context.MODE_PRIVATE)
//            val editorName = sharedPreferencesName.edit()
//            editorName.apply {
//                putString("sharedPrefsName", name)
//            }.apply()
//
//
//            var bDate = birthDateTextField.editText?.text.toString()
//            val sharedPreferencesBDate = getSharedPreferences("sharedPrefs_bDate", Context.MODE_PRIVATE)
//            val editorBDate = sharedPreferencesBDate.edit()
//            editorBDate.apply {
//                putString("sharedPrefs_bDate", bDate)
//            }.apply()
//
//
//
//            //Snackbar.make(it, "$name $bDate $disease", Snackbar.LENGTH_SHORT).show()
//
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }


    }

    private fun btnYear() {
        var today: Calendar = Calendar.getInstance()
        var builder: MonthPickerDialog.Builder = MonthPickerDialog.Builder(
            this@RegisterActivity,
            { selectedMonth, selectedYear ->
                run {
                    user_year_txt.text = selectedYear.toString()
                }
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH)
        )
        builder.setActivatedMonth(Calendar.JANUARY)
            .setMinYear(2484)
            .setActivatedYear(today.get(Calendar.YEAR)+543)
            .setMaxYear(today.get(Calendar.YEAR)+543)
            .setTitle("เลือกปีเกิดของคุณ")
            .showYearOnly()
            .build().show()
    }
}
//            var disease = diseaseTextField.editText?.text.toString()
//            val sharedPreferencesDisease = getSharedPreferences("sharedPrefsDisease", Context.MODE_PRIVATE)
//            val editorDisease = sharedPreferencesDisease.edit()
//            editorDisease.apply {
//                putString("sharedPrefsDisease", disease)
//            }.apply()