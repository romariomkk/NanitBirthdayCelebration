package com.romariomkk.nanitbirth.di.module

import com.romariomkk.nanitbirth.config.initializer.Initializer
import com.romariomkk.nanitbirth.config.initializer.Jodainitializer
import com.romariomkk.nanitbirth.config.initializer.TimberInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ApplicationComponent::class)
class InitializerModule {

    @Provides
    @IntoSet
    fun initTimber(): Initializer = TimberInitializer()

    @Provides
    @IntoSet
    fun initJoda() : Initializer = Jodainitializer()

}