package com.romariomkk.nanitbirth.domain.usecase

import com.romariomkk.nanitbirth.data.repo.contract.ChildStorage
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildInfo
import com.romariomkk.nanitbirth.util.ext.toChild
import javax.inject.Inject

class GetChildInfoImpl @Inject constructor(
    private val childStorage: ChildStorage
): GetChildInfo {

    override fun execute() =
        childStorage.getChild()
            .toChild()
}