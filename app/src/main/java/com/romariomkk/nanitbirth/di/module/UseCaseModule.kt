package com.romariomkk.nanitbirth.di.module

import com.romariomkk.nanitbirth.domain.usecase.GetChildInfoImpl
import com.romariomkk.nanitbirth.domain.usecase.GetChildLifeInfoImpl
import com.romariomkk.nanitbirth.domain.usecase.UpdateChildInfoImpl
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildLifeInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetChildInfo(getChildInfo: GetChildInfoImpl): GetChildInfo

    @Binds
    fun bindUpdateChildInfo(updateChildInfo: UpdateChildInfoImpl): UpdateChildInfo

    @Binds
    fun bindChildLifeInfo(childLifeInfo: GetChildLifeInfoImpl): GetChildLifeInfo


}