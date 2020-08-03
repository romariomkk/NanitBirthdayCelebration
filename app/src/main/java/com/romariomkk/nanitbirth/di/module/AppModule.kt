package com.romariomkk.nanitbirth.di.module

import android.app.Application
import android.content.Context
import com.romariomkk.nanitbirth.NanitBirthApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(includes = [
    RepositoryModule::class,
    UseCaseModule::class,
    InitializerModule::class])
@InstallIn(ApplicationComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun provideApp(application: Application): NanitBirthApp = application as NanitBirthApp

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}
