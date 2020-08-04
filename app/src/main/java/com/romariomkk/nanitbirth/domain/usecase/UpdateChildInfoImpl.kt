package com.romariomkk.nanitbirth.domain.usecase

import com.romariomkk.nanitbirth.data.storage.contract.ChildStorage
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import com.romariomkk.nanitbirth.util.DateUtils.asString
import java.util.*
import javax.inject.Inject

class UpdateChildInfoImpl @Inject constructor(
    private val childStorage: ChildStorage
): UpdateChildInfo {

    override fun updateName(name: String) =
        childStorage.updateName(name)

    override fun updateImageUri(imageUri: String) =
        childStorage.updateChildImageUri(imageUri)

    override fun updateBirthDate(birthDate: Date) {
        childStorage.updateBirthDate(birthDate.asString())
    }
}