package com.romariomkk.nanitbirth.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.DatePicker

class CustomDatePicker @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : DatePicker(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            val p = parent
            p?.requestDisallowInterceptTouchEvent(true)
        }
        return false
    }
}