package com.romariomkk.nanitbirth.util

import org.joda.time.DateTime
import org.joda.time.Months
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.util.*


object Keys {

    const val STORAGE_NAME = "nanit_storage"

    private const val BIRTH_DATE_FORMAT = "dd/MM/yyyy"
    private val dateFormatter = SimpleDateFormat(BIRTH_DATE_FORMAT, Locale.ENGLISH)

    fun String.asDate() =
        dateFormatter.parse(this)!!

    fun Date.asString(): String =
        dateFormatter.format(this)

    @JvmStatic
    fun getFullMonths(birthDate: String): Array<Int> {
        val birth = DateTime.parse(birthDate, DateTimeFormat.forPattern(BIRTH_DATE_FORMAT)).withTime(0, 0, 0, 0)
        val now = DateTime.now().withTime(0, 0, 0, 0)
        val months = Months.monthsBetween(birth, now).months

        val years = months / 12
        val remainingMonths = months - years * 12
        return arrayOf(years, remainingMonths)
    }

    @JvmStatic
    fun isToday(date: String): Boolean {
        val birth =
            DateTime.parse(date, DateTimeFormat.forPattern(BIRTH_DATE_FORMAT)).withTime(0, 0, 0, 0)
        val now = DateTime.now().withTime(0, 0, 0, 0)
        return birth.toLocalDate().compareTo(now.toLocalDate()) == 0
    }
}