package com.romariomkk.nanitbirth.di.module

import android.content.Context
import android.content.SharedPreferences
import com.romariomkk.nanitbirth.data.repo.PrefsStorageImpl
import com.romariomkk.nanitbirth.data.repo.contract.ChildStorage
import com.romariomkk.nanitbirth.util.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideSharePrefInstance(context: Context) =
        context.getSharedPreferences(Keys.STORAGE_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    internal fun provideStorageImpl(sharedPreferences: SharedPreferences) =
        PrefsStorageImpl(sharedPreferences)

    @Singleton
    @Provides
    internal fun provideAuthStorage(prefStorageImpl: PrefsStorageImpl): ChildStorage =
        prefStorageImpl
}