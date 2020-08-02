package com.romariomkk.nanitbirth.di.module

import com.romariomkk.nanitbirth.domain.usecase.GetChildInfoImpl
import com.romariomkk.nanitbirth.domain.usecase.UpdateChildImageImpl
import com.romariomkk.nanitbirth.domain.usecase.UpdateChildInfoImpl
import com.romariomkk.nanitbirth.domain.usecase.contract.GetChildInfo
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildImage
import com.romariomkk.nanitbirth.domain.usecase.contract.UpdateChildInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface UseCaseModule {

    @Binds
    fun bindGetChildInfo(getChildInfo: GetChildInfoImpl): GetChildInfo

    @Binds
    fun bindUpdateChildInfo(updateChildInfo: UpdateChildInfoImpl): UpdateChildInfo

    @Binds
    fun bindUpdateChildImage(updateChildImage: UpdateChildImageImpl): UpdateChildImage
}