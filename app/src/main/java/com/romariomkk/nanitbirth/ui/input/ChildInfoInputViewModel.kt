package com.romariomkk.nanitbirth.ui.input

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import com.romariomkk.nanitbirth.ui.base.AbsViewModel
import com.romariomkk.nanitbirth.util.ext.isNotVoid
import java.util.*

class ChildInfoInputViewModel @ViewModelInject constructor(
    private val getChildInfo: GetChildInfo,
    private val updateChildInfo: UpdateChildInfo
) : AbsViewModel() {

    val observer = Observer()

    val name = MutableLiveData<String>()
    val birthDate = MutableLiveData<Date>()
    val imageUri = MutableLiveData<String>()

    val isCongratsEnabled = MutableLiveData(false)

    override fun onAttached() {
        super.onAttached()
        requestChildInfo()

        birthDate.observeForever(birthDateObserver)
        imageUri.observeForever(imageUriObserver)
        name.observeForever(nameObserver)
    }

    fun updateChildImage(imageUri: String) {
        updateChildInfo.updateImageUri(imageUri)
    }

    private fun requestChildInfo() {
        val child = getChildInfo.execute()
        birthDate.value = child.birthDate
        imageUri.value = child.imageUri
        name.value = child.name
    }

    private val nameObserver = Observer<String> {
        if (!it.isNullOrEmpty() && !it.isNullOrBlank()) {
            updateChildInfo.updateName(it)
            updateCongratsAvailability()
        }
    }
    private val birthDateObserver = Observer<Date> { date ->
        date?.let {
            updateChildInfo.updateBirthDate(it)
            updateCongratsAvailability()
        }
    }
    private val imageUriObserver = Observer<String> {
        if (!it.isNullOrEmpty() && !it.isNullOrBlank()) {
            updateChildInfo.updateImageUri(it)
        }
    }

    private fun updateCongratsAvailability() {
        isCongratsEnabled.value = birthDate.value != null && name.isNotVoid()
    }

    override fun onCleared() {
        super.onCleared()
        birthDate.removeObserver(birthDateObserver)
        imageUri.removeObserver(imageUriObserver)
        name.removeObserver(nameObserver)
    }


    inner class Observer: LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            requestChildInfo()
        }
    }

}