package com.romariomkk.nanitbirth.domain.usecase

import com.romariomkk.nanitbirth.data.repo.contract.ChildStorage
import com.romariomkk.nanitbirth.domain.pojo.Child
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import com.romariomkk.nanitbirth.util.ext.toChildInfo
import javax.inject.Inject

class UpdateChildInfoImpl @Inject constructor(
    private val childStorage: ChildStorage
): UpdateChildInfo {

    override fun execute(child: Child) =
        childStorage.updateChild(child.toChildInfo())
}