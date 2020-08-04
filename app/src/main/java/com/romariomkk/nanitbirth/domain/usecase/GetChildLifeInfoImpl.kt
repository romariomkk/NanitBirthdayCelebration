package com.romariomkk.nanitbirth.domain.usecase

import com.romariomkk.nanitbirth.data.storage.contract.ChildStorage
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildLifeInfo
import com.romariomkk.nanitbirth.util.ext.toChildLife
import javax.inject.Inject

class GetChildLifeInfoImpl @Inject constructor(
    private val childStorage: ChildStorage
): GetChildLifeInfo {

    override fun execute() =
        childStorage.getChild()
            .toChildLife()
}