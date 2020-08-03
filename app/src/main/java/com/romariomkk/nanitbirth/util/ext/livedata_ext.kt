package com.romariomkk.nanitbirth.util.ext

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<String>.isNotVoid() =
    value!!.isNotBlank() && value!!.isNotEmpty()