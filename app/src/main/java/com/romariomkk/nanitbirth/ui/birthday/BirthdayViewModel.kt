package com.romariomkk.nanitbirth.ui.birthday

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.romariomkk.nanitbirth.domain.pojo.ChildLife
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildLifeInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import com.romariomkk.nanitbirth.ui.base.AbsViewModel
import java.util.concurrent.ThreadLocalRandom

class BirthdayViewModel @ViewModelInject constructor(
    private val updateChildInfo: UpdateChildInfo,
    private val getChildLifeInfo: GetChildLifeInfo
): AbsViewModel() {

    val uiMode = MutableLiveData<BirthdayUiState>()
    private val random = ThreadLocalRandom.current()

    val child = MutableLiveData<ChildLife>()

    override fun onAttached() {
        super.onAttached()
        uiMode.value = getRandomUiState()
        child.value = getChildLifeInfo.execute()
    }

    private fun getRandomUiState() = BirthdayUiState.all[random.nextInt(BirthdayUiState.all.size)]

    fun updateChildImage(imageUri: String) {
        updateChildInfo.updateImageUri(imageUri)
        child.value = getChildLifeInfo.execute()
    }
}