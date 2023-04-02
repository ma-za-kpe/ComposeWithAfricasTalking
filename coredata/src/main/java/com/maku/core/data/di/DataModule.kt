package com.maku.core.data.di

import com.maku.core.data.repo.ATComposeRepository
import com.maku.core.data.repo.ATComposeRepositoryImpl
import com.maku.core.network.repo.RemoteDataSource
import com.maku.core.network.repo.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAtComposeRepository(repository: ATComposeRepositoryImpl): ATComposeRepository

    @Binds
    abstract fun bindNetworkDataStore(cache: RemoteDataSourceImpl): RemoteDataSource
}
