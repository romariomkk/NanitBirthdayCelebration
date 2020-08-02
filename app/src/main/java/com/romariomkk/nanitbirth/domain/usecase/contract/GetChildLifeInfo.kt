package com.romariomkk.nanitbirth.domain.usecase.contract

import com.romariomkk.nanitbirth.domain.pojo.ChildLife

interface GetChildLifeInfo {

    fun execute(): ChildLife
}