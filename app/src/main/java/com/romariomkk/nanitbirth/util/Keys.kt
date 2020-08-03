package com.romariomkk.nanitbirth.util

import org.joda.time.DateTime
import org.joda.time.Months
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Keys {

    const val STORAGE_NAME = "nanit_storage"

    private const val BIRTH_DATE_FORMAT = "dd/MM/yyyy"
    private val dateFormatter = SimpleDateFormat(BIRTH_DATE_FORMAT, Locale.ENGLISH)

    fun String.asDate(): Date? {
        return try {
            dateFormatter.parse(this)!!
        } catch (e: ParseException) {
            Timber.e(e)
            null
        }
    }

    fun Date.asString(): String =
        dateFormatter.format(this)

    @JvmStatic
    fun getFullMonths(birthDate: String): Array<Int> {
        val birth = DateTime.parse(birthDate, DateTimeFormat.forPattern(BIRTH_DATE_FORMAT))
            .withTime(0, 0, 0, 0)
        val now = DateTime.now().withTime(0, 0, 0, 0)
        val months = Months.monthsBetween(birth, now).months
        val years = Years.yearsBetween(birth, now).years

        val remainingMonths = months - years * 12
        return arrayOf(years, remainingMonths)
    }

    @JvmStatic
    fun isBirthdayToday(date: String): Boolean {
        val birth =
            DateTime.parse(date, DateTimeFormat.forPattern(BIRTH_DATE_FORMAT)).withTime(0, 0, 0, 0)
        val now = DateTime.now().withTime(0, 0, 0, 0)
        val years = Years.yearsBetween(birth, now).years

        return if (years == 0) {
            birth.dayOfMonth == now.dayOfMonth
        } else {
            birth.dayOfMonth == now.dayOfMonth &&
                    birth.monthOfYear == now.monthOfYear
        }

    }
}