package com.example.apphwttm

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class myDateInTH {
    fun myDateTodayInTHfun(): String {
        var day: String
        var month: String
        var year: String
        val date = LocalDate.now().toString().split("-")
        day = date[2]
        year = (date[0].toInt() + 543).toString()
        month = date[1]
        var monthText = ""
        when (month) {
            "01" -> monthText = "มกราคม"
            "02" -> monthText = "กุมภาพันธ์"
            "03" -> monthText = "มีนาคม"
            "04" -> monthText = "เมษายน"
            "05" -> monthText = "พฤษภาคม"
            "06" -> monthText = "มิถุนายน"
            "07" -> monthText = "กรกฎาคม"
            "08" -> monthText = "สิงหาคม"
            "09" -> monthText = "กันยายน"
            "10" -> monthText = "ตุลาคม"
            "11" -> monthText = "พฤศจิกายน"
            "12" -> monthText = "ธันวาคม"
            else -> {
                monthText = "ธันวาคม"
            }
        }

        val toDay = "$day $monthText $year"
        return toDay
    }

    fun calendarDateInTH(): String {
        var date = LocalDate.now()
        val date2 = LocalDate.now().toString().split("-")
        var day: String = date.dayOfWeek.toString()
        var dayTxt = ""
        when(day){
            "FRIDAY" -> dayTxt = "วันศุกร์"
            "MONDAY" -> dayTxt = "วันจันทร์"
            "SATURDAY" -> dayTxt = "วันเสาร์"
            "SUNDAY" -> dayTxt = "วันอาทิตย์"
            "THURSDAY" -> dayTxt = "วันพฤหัสบดี"
            "TUESDAY" -> dayTxt = "วันอังคาร"
            "WEDNESDAY" -> dayTxt = "วันพุธ"
        }
        var month: String = date2[1]
        var year: String = (date2[0].toInt() + 543).toString()
        var monthTxt = ""
        when (month) {
            "01" -> monthTxt = "มกราคม"
            "02" -> monthTxt = "กุมภาพันธ์"
            "03" -> monthTxt = "มีนาคม"
            "04" -> monthTxt = "เมษายน"
            "05" -> monthTxt = "พฤษภาคม"
            "06" -> monthTxt = "มิถุนายน"
            "07" -> monthTxt = "กรกฎาคม"
            "08" -> monthTxt = "สิงหาคม"
            "09" -> monthTxt = "กันยายน"
            "10" -> monthTxt = "ตุลาคม"
            "11" -> monthTxt = "พฤศจิกายน"
            "12" -> monthTxt = "ธันวาคม"
            else -> {
                monthTxt = "ธันวาคม"
            }
        }
        return "$dayTxt $monthTxt $year"
    }
}