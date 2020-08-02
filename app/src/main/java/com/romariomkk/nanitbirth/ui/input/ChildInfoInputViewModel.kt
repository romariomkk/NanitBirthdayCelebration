package com.romariomkk.nanitbirth.ui.input

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import com.romariomkk.nanitbirth.ui.base.AbsViewModel
import java.util.*

class ChildInfoInputViewModel @ViewModelInject constructor(
    private val getChildInfo: GetChildInfo,
    private val updateChildInfo: UpdateChildInfo
) : AbsViewModel() {

    val name = MutableLiveData<String>()
    val birthDate = MutableLiveData<Date>()
    val imageUri = MutableLiveData<String>()

    val isCongratsEnabled = MutableLiveData(false)

    override fun onAttached() {
        super.onAttached()
        val child = getChildInfo.execute()
        birthDate.value = child.birthDate
        imageUri.value = child.imageUri
        name.value = child.name

        birthDate.observeForever(birthDateObserver)
        imageUri.observeForever(imageUriObserver)
        name.observeForever(nameObserver)
    }

    private val nameObserver = Observer<String> {
        updateChildInfo.updateName(it)
        updateCongratsAvailability()
    }
    private val birthDateObserver = Observer<Date> {
        updateChildInfo.updateBirthDate(it)
        updateCongratsAvailability()
    }
    private val imageUriObserver = Observer<String> {
        updateChildInfo.updateImageUri(it)
    }

    private fun updateCongratsAvailability() {
        isCongratsEnabled.value =
            (imageUri.value!!.isNotEmpty() && imageUri.value!!.isNotBlank()) and
                    (name.value!!.isNotEmpty() && name.value!!.isNotBlank())
    }

    override fun onCleared() {
        super.onCleared()
        birthDate.removeObserver(birthDateObserver)
        imageUri.removeObserver(imageUriObserver)
        name.removeObserver(nameObserver)
    }

}