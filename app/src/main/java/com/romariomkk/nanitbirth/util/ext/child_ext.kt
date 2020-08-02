package com.romariomkk.nanitbirth.util.ext

import com.romariomkk.nanitbirth.data.pojo.ChildInfo
import com.romariomkk.nanitbirth.domain.pojo.Child
import com.romariomkk.nanitbirth.domain.pojo.ChildLife
import com.romariomkk.nanitbirth.util.Keys
import com.romariomkk.nanitbirth.util.Keys.asDate

fun ChildInfo.toChild() =
    Child(name, birthDate.asDate(), imageUri)

fun ChildInfo.toChildLife(): ChildLife {
    val (years, months) = Keys.getFullMonths(birthDate)
    return ChildLife(name, Keys.isToday(birthDate), years, months, imageUri)
}
