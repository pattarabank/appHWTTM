package com.example.apphwttm.calendar

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.apphwttm.R
import com.example.apphwttm.myDateInTH
import com.google.android.material.snackbar.Snackbar

class CalendarDetailBadActivity : AppCompatActivity() {

    private lateinit var txtViewName: TextView
    private lateinit var txtViewDate: TextView
    private lateinit var txtViewBadDisease: TextView
    private lateinit var txtViewDetailHeader: TextView
    private lateinit var txtViewDetail: TextView

    private lateinit var savePicBtn: Button
    private lateinit var shareBtn: Button

    private lateinit var calDetailBadBackBtn: TextView

    var MY_PERMISSIONS_REQUEST_READ_CONTACTS = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_detail_bad)

        requestPermission()

        //set back btn
        calDetailBadBackBtn = findViewById(R.id.myPreviousIconCalendarBad)
        calDetailBadBackBtn.setOnClickListener {
            onBackPressed()
        }

        val key: String? = intent.getStringExtra("key_to_bad")
        val setDate: String? = intent.getStringExtra("date_to_bad")
        var str1: List<String> = emptyList()
        var detailTxt: String = ""
        //Log.d("TESTSET", key.toString())
        val shPWithDetail = getSharedPreferences("USERDATAWITHDETAIL", Context.MODE_PRIVATE)
        val shPWithDetailData = shPWithDetail.getString(key, null)
        Log.d("TESTSETt", shPWithDetailData.toString())
        if (shPWithDetailData == null) {
            val shPWithOutDetail = getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
            val shPWithOutDetailData = shPWithOutDetail.getString(key, null)
            Log.d("TESTSETt", shPWithOutDetailData.toString())
            str1 = shPWithOutDetailData!!.split("+")
        } else {
            str1 = shPWithDetailData!!.split("+")
            detailTxt = str1?.last()
        }


        val myList: String = str1[0]
        val myListTxt = myList.split("-")
        //var size = myListTxt.size - 1
        var setTextmyList = ""
        for (temp in myListTxt)
            if (temp == myListTxt[myListTxt.size - 1]) {
                continue
            } else {
                setTextmyList += "$temp "
            }


        //Log.d("TESTSET", setTextmyList)

        //name
        txtViewName = findViewById(R.id.txtViewNameB)
        var shPName = getSharedPreferences("userName", Context.MODE_PRIVATE)
        var shPNameData = shPName.getString("userName", null)
        txtViewName.text = shPNameData

        //date
        txtViewDate = findViewById(R.id.textViewDateB)
        var dayArr = myDateInTH().calendarDateInTH().split(" ")
        var dayNumArr = myDateInTH().myDateTodayInTHfun().split(" ")
        //var setDate = "${dayArr[0]} ที่ ${dayNumArr[0]} ${dayArr[1]} ${dayArr[2]}"
        txtViewDate.text = setDate

        //lisiOfdisease
        txtViewBadDisease = findViewById(R.id.textViewBadDisease)
        txtViewBadDisease.text = setTextmyList

        //header
        txtViewDetailHeader = findViewById(R.id.textView5)
        txtViewDetailHeader.text = "รายละเอียดเพิ่มเติม"

        //detail
        txtViewDetail = findViewById(R.id.textView6)
        txtViewDetail.text = detailTxt

        //btn savepic
        savePicBtn = findViewById(R.id.buttonBad1)
        savePicBtn.setOnClickListener {
            var shareBitmap = getBitmapFromView(it.rootView)
            var shareUri = getUriFromBitmap(shareBitmap)
            Snackbar.make(it, "บันทึกภาพสำเร็จ", Snackbar.LENGTH_SHORT).show()
        }
        //btn share
        shareBtn = findViewById(R.id.buttonBad2)
        shareBtn.setOnClickListener {
            var shareBitmap = getBitmapFromView(it.rootView)
            var shareUri = getUriFromBitmap(shareBitmap)
            shareImage(shareUri)
        }

    }

    private fun shareImage(imageUri: Uri) {
        val share = Intent(Intent.ACTION_SEND)
        share.putExtra(Intent.EXTRA_TEXT, "HealthActivityPicture")
        share.putExtra(Intent.EXTRA_STREAM, imageUri)
        share.type = "image/*"
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(share, "Share Image"))
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)
        return returnedBitmap
    }

    private fun getUriFromBitmap(bitmap: Bitmap): Uri {
        val path =
            MediaStore.Images.Media.insertImage(
                this.contentResolver,
                bitmap,
                "HealthActivityPicture",
                null
            )
        return Uri.parse(path)
    }

    private fun requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

}