package com.romariomkk.nanitbirth.data.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.romariomkk.nanitbirth.data.pojo.ChildInfo
import com.romariomkk.nanitbirth.data.storage.contract.ChildStorage
import timber.log.Timber
import java.io.File

@SuppressLint("ApplySharedPref")
class PrefsStorageImpl(
    private val sharedPrefs: SharedPreferences
) : ChildStorage {

    override fun getChild(): ChildInfo {
        val name = sharedPrefs.getString(ChildStorage.KEY_CHILD_NAME, "")!!
        val birthDate = sharedPrefs.getString(ChildStorage.KEY_CHILD_BIRTH_DATE, "")!!
        var imageUri = sharedPrefs.getString(ChildStorage.KEY_CHILD_IMAGE_URI, "")!!

        if (imageUri.isNotEmpty() && !File(imageUri).exists()) {
            Timber.e("Removed reference to $imageUri")
            sharedPrefs.edit().remove(ChildStorage.KEY_CHILD_IMAGE_URI).commit()
            imageUri = ""
        }
        return ChildInfo(name, birthDate, imageUri)
    }

    override fun updateChildImageUri(imageUri: String) {
        sharedPrefs.edit().putString(ChildStorage.KEY_CHILD_IMAGE_URI, imageUri).commit()
    }

    override fun updateBirthDate(birthDate: String) {
        sharedPrefs.edit().putString(ChildStorage.KEY_CHILD_BIRTH_DATE, birthDate).commit()
    }

    override fun updateName(name: String) {
        sharedPrefs.edit().putString(ChildStorage.KEY_CHILD_NAME, name).commit()
    }
}