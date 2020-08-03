package com.romariomkk.nanitbirth.util.binding

import android.widget.DatePicker
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.util.*

object BirthdayInputBinding {

    @JvmStatic
    @BindingAdapter("dateAttrChanged")
    fun DatePicker.bindListener(listener: InverseBindingListener?) {
        listener?.let {
            setOnDateChangedListener { _, _, _, _ ->
                listener.onChange()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("date")
    fun DatePicker.bindDateToDatePicker(date: Date?) {
        val cal = Calendar.getInstance().apply { time = date ?: Date() }
        val newYear = cal.get(Calendar.YEAR)
        val newMonth = cal.get(Calendar.MONTH)
        val newDay = cal.get(Calendar.DAY_OF_MONTH)
        if (newYear != year || newMonth != month || newDay != dayOfMonth) {
            updateDate(newYear, newMonth, newDay)
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "date")
    fun DatePicker.inverseBindDatePicker(): Date {
        val calendar = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
        return calendar.time
    }
}