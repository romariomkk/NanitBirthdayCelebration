package com.romariomkk.nanitbirth.domain.usecase.contract

import java.util.*

interface UpdateChildInfo {

    fun updateName(name: String)

    fun updateImageUri(imageUri: String)

    fun updateBirthDate(birthDate: Date)
}