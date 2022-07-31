package com.d108.sduty.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatUtil {
    companion object{
        fun converYYYYMMDD(date: String): Date{
            val formatter = SimpleDateFormat("yyyyMMdd")
            formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
            return formatter.parse(date)
        }
    }
}