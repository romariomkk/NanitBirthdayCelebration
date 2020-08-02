package com.romariomkk.nanitbirth.ui.birthday

import androidx.lifecycle.MutableLiveData
import com.romariomkk.nanitbirth.domain.pojo.ChildLife
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildLifeInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildImage
import com.romariomkk.nanitbirth.ui.base.AbsViewModel
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

class BirthdayViewModel @Inject constructor(
    private val updateChildImage: UpdateChildImage,
    private val getChildInfo: GetChildLifeInfo
): AbsViewModel() {

    val uiMode = MutableLiveData<BirthdayUiState>()
    private val random = ThreadLocalRandom.current()

    val child = MutableLiveData<ChildLife>()

    override fun onAttached() {
        super.onAttached()
        uiMode.value = getRandomUiState()
        child.value = getChildInfo.execute()
    }

    private fun getRandomUiState() = BirthdayUiState.all[random.nextInt(BirthdayUiState.all.size)]

    private fun updateChildImage(imageUri: String) {
        updateChildImage.execute(imageUri)
    }
}