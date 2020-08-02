package com.romariomkk.nanitbirth.ui.birthday

sealed class BirthdayUiState {
    object Elephant: BirthdayUiState()
    object Fox: BirthdayUiState()
    object Pelican: BirthdayUiState()

    companion object {
        val all = arrayOf(Elephant, Fox, Pelican)
    }
}