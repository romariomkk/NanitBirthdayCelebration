package com.romariomkk.nanitbirth.domain.pojo

data class ChildLife(
    val name: String,
    val isBornToday: Boolean,
    val fullYears: Int,
    val remainingMonths: Int,
    val imageUri: String
)