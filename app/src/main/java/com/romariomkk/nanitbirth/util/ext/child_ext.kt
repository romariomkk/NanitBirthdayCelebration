package com.romariomkk.nanitbirth.util.ext

import com.romariomkk.nanitbirth.data.pojo.ChildInfo
import com.romariomkk.nanitbirth.domain.pojo.Child
import com.romariomkk.nanitbirth.domain.pojo.ChildLife
import com.romariomkk.nanitbirth.util.DateUtils
import com.romariomkk.nanitbirth.util.DateUtils.asDate

fun ChildInfo.toChild() =
    Child(name, birthDate.asDate(), imageUri)

fun ChildInfo.toChildLife(): ChildLife {
    val (years, months) = DateUtils.getFullMonths(birthDate)
    return ChildLife(name, DateUtils.isBirthdayToday(birthDate), years, months, imageUri)
}
