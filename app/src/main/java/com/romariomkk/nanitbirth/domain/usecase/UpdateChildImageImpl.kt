package com.romariomkk.nanitbirth.domain.usecase

import com.romariomkk.nanitbirth.data.repo.contract.ChildStorage
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildImage
import javax.inject.Inject

class UpdateChildImageImpl @Inject constructor(
    private val childStorage: ChildStorage
) : UpdateChildImage {

    override fun execute(imageUri: String) =
        childStorage.updateChildImageUri(imageUri)

}