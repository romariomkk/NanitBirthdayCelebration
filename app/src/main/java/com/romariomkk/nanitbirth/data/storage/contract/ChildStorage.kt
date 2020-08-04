package com.romariomkk.nanitbirth.data.storage.contract

import com.romariomkk.nanitbirth.data.pojo.ChildInfo

interface ChildStorage {

    fun getChild(): ChildInfo

    fun updateChildImageUri(imageUri: String)

    fun updateBirthDate(birthDate: String)

    fun updateName(name: String)

    companion object {
        const val KEY_CHILD_NAME = "key_child_name"
        const val KEY_CHILD_BIRTH_DATE = "key_child_birth_date"
        const val KEY_CHILD_IMAGE_URI = "key_child_image_uri"
    }
}