package com.kartik.test.schooluidemoproject.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import com.kartik.test.schooluidemoproject.R
import java.util.*

object DatePickerUtility {
    @SuppressLint("SetTextI18n")
    fun showDatePicker(
        pContext: Context?,
        pDatePickerInterface: DatePickerInterface,
        pDate: Date? = null
    ) {
        if (pContext == null) {
            return
        }
        val calender = Calendar.getInstance()
        val calendarNew = Calendar.getInstance()
        var year = calender.get(Calendar.YEAR)
        var month = calender.get(Calendar.MONTH)
        var day = calender.get(Calendar.DAY_OF_MONTH) - 1

        calendarNew.set(Calendar.DAY_OF_MONTH, day)
        if (pDate != null) {
            calender.time = pDate
            year = calender.get(Calendar.YEAR)
            month = calender.get(Calendar.MONTH)
            day = calender.get(Calendar.DAY_OF_MONTH)
        }

        val datePickerDialog = DatePickerDialog(
            pContext, R.style.customDatePickerDialog,
            DatePickerDialog.OnDateSetListener { _, yearGet, monthOfYear, dayOfMonth ->
                var monthValue = monthOfYear
                monthValue++
                pDatePickerInterface.onDateGet(dayOfMonth, yearGet, monthValue)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = calendarNew.timeInMillis
        datePickerDialog.show()
    }

    interface DatePickerInterface {
        fun onDateGet(pDayOfMonth: Int, pYear: Int, pMonth: Int)
    }
}