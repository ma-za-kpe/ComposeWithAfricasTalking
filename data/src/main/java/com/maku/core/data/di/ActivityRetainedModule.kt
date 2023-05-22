package com.maku.core.data.di

import com.maku.core.data.repo.ATComposeRepositoryImpl
import com.maku.core.network.repo.ATComposeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun bindAtComposeRepository(repository: ATComposeRepositoryImpl): ATComposeRepository
}
