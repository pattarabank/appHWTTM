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
import com.example.apphwttm.admin.AdminActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.io.IOException
import java.lang.Exception
import java.util.*

class RegisterActivity : AppCompatActivity() {

    //    val sharedPreferencesName = getSharedPreferences("sharedPrefsName", Context.MODE_PRIVATE)
//    val editorName = sharedPreferencesName.edit()
//    editorName.apply {
//        putString("sharedPrefsName", name)
//    }.apply()

    private lateinit var regisYearBtn: ExtendedFloatingActionButton
    private lateinit var myCalendar: TextView
    private lateinit var user_year_txt: TextView
    private lateinit var user_name_txt: TextInputEditText
    private lateinit var regis_setting_ic: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        myCalendar = findViewById(R.id.regis_txt_calendar)
        myCalendar.setOnClickListener {
            btnYear()
        }
        regisYearBtn = findViewById(R.id.extended_fab)
        user_year_txt = findViewById(R.id.user_year_txt)
        user_year_txt.text = null
        user_year_txt.setOnClickListener {
            btnYear()
        }
        user_name_txt = findViewById(R.id.user_name_input_txt)
        user_name_txt.text = null
        regisYearBtn.setOnClickListener {
            try {
                if (user_name_txt.text != null && user_year_txt != null) {
                    //save data
                    var name = user_name_txt.text.toString()
                    val sharedPreferencesName =
                        getSharedPreferences("userName", Context.MODE_PRIVATE)
                    val editorName = sharedPreferencesName.edit()
                    editorName.apply {
                        putString("userName", name)
                    }.apply()

                    var year = user_year_txt.text.toString()
                    val sharedPreferencesYear =
                        getSharedPreferences("userYear", Context.MODE_PRIVATE)
                    val editorYear = sharedPreferencesYear.edit()
                    editorYear.apply {
                        putString("userYear", year)
                    }.apply()

                    //isKid อายุน้อยกว่า 12
                    var thisYear: Calendar = Calendar.getInstance()
                    var numYear = thisYear.get(Calendar.YEAR)
                    numYear += 543
                    val sharedPreferencesIsKid = getSharedPreferences("isKid", Context.MODE_PRIVATE)
                    if ((numYear - year.toInt()) <= 12) {
                        val editorIsKid = sharedPreferencesIsKid.edit()
                        editorIsKid.apply {
                            putBoolean("isKid", true)
                        }.apply()
                        Log.d("testUserAge", "kid")
                    } else {
                        val editorIsKid = sharedPreferencesIsKid.edit()
                        editorIsKid.apply {
                            putBoolean("isKid", false)
                        }.apply()
                        Log.d("testUserAge", "adult")
                    }
                    Log.d("testUserAge", ((numYear - year.toInt()).toString()))

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    finish()

                } else {
                    Snackbar.make(it, "กรอกไม่ครบ", Snackbar.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Snackbar.make(it, "กรอกไม่ครบ", Snackbar.LENGTH_SHORT).show()
            }


        }

        regis_setting_ic = findViewById(R.id.regis_settings_ic)
        regis_setting_ic.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

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
            .setActivatedYear(today.get(Calendar.YEAR) + 543)
            .setMaxYear(today.get(Calendar.YEAR) + 543)
            .setTitle("เลือกปีเกิดของคุณ")
            .showYearOnly()
            .build().show()
    }
}