package com.maku.core.data.di

import android.content.Context
import androidx.room.Room
import com.maku.core.data.local.ComposeWithATDatabase
import com.maku.core.data.local.dao.AirTimeDao
import com.maku.core.data.local.repo.LocalStorage
import com.maku.core.data.local.repo.LocalStorageImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageModule {

    @Binds
    abstract fun bindCache(cache: LocalStorageImpl): LocalStorage

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): ComposeWithATDatabase {
            return Room.databaseBuilder(
                context,
                ComposeWithATDatabase::class.java,
                "localStorage.db"
            ).build()
        }

        @Provides
        fun provideAirTimeDao(
            composeWithATDatabase: ComposeWithATDatabase
        ): AirTimeDao = composeWithATDatabase.airtimeDao()
    }
}
