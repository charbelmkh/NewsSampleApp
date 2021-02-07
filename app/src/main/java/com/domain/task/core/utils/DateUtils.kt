package com.domain.task.core.utils


import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {


    companion object {
        private const val DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ssZ"
        private val sdf = SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.getDefault())
        private val months: Array<String> by lazy {
            DateFormatSymbols().months
        }

        fun formatDate(date: String?): String {
            date?.let {
                try {
                    val cal = Calendar.getInstance()
                    val parsedData = sdf.parse(date)
                    if (parsedData != null) {
                        cal.time = parsedData
                        return "${months[cal.get(Calendar.MONTH)]} ${cal.get(Calendar.DAY_OF_MONTH)}, ${
                            cal.get(
                                Calendar.YEAR
                            )
                        }"

                    }
                } catch (e: Exception) {
                }
            }
            return ""
        }


    }
}