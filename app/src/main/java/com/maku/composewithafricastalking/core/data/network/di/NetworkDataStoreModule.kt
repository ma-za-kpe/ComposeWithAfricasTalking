package com.maku.composewithafricastalking.core.data.network.di

import com.maku.composewithafricastalking.core.data.repo.ATComposeRepository
import com.maku.composewithafricastalking.core.data.repo.ATComposeRepositoryImpl
import com.maku.composewithafricastalking.core.data.repo.RemoteDataSource
import com.maku.composewithafricastalking.core.data.repo.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkDataStoreModule {

    @Binds
    abstract fun bindZontoRepository(repository: ATComposeRepositoryImpl): ATComposeRepository

    @Binds
    abstract fun bindNetworkDataStore(cache: RemoteDataSourceImpl): RemoteDataSource

}
