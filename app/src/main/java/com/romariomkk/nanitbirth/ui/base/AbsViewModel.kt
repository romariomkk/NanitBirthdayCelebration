package com.romariomkk.nanitbirth.ui.base

import androidx.lifecycle.ViewModel

abstract class AbsViewModel : ViewModel() {

    open fun onAttached() {}
}