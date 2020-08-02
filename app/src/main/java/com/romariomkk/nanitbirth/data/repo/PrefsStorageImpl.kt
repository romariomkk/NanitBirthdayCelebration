package com.romariomkk.nanitbirth.data.repo

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.romariomkk.nanitbirth.data.pojo.ChildInfo
import com.romariomkk.nanitbirth.data.repo.contract.ChildStorage

@SuppressLint("ApplySharedPref")
class PrefsStorageImpl(
    private val sharedPrefs: SharedPreferences
) : ChildStorage {

    override fun getChild(): ChildInfo {
        val name = sharedPrefs.getString(ChildStorage.KEY_CHILD_NAME, "")!!
        val birthDate = sharedPrefs.getString(ChildStorage.KEY_CHILD_BIRTH_DATE, "")!!
        val imageUri = sharedPrefs.getString(ChildStorage.KEY_CHILD_IMAGE_URI, "")!!
        return ChildInfo(name, birthDate, imageUri)
    }

    override fun updateChild(child: ChildInfo) {
        sharedPrefs.edit().apply {
            putString(ChildStorage.KEY_CHILD_NAME, child.name)
            putString(ChildStorage.KEY_CHILD_BIRTH_DATE, child.birthDate)
            putString(ChildStorage.KEY_CHILD_IMAGE_URI, child.imageUri)
        }.commit()
    }

    override fun updateChildImageUri(imageUri: String) {
        sharedPrefs.edit()
            .putString(ChildStorage.KEY_CHILD_IMAGE_URI, imageUri)
            .commit()
    }
}