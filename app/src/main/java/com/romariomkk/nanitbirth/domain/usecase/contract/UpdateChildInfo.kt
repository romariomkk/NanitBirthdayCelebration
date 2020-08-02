package com.romariomkk.nanitbirth.domain.usecase.contract

import com.romariomkk.nanitbirth.domain.pojo.Child

interface UpdateChildInfo {

    fun execute(child: Child)
}