package com.maku.core.data.repo

import com.maku.core.network.repo.RemoteDataSource

// Abstractions for the different data sources, or services used in your app
interface ATComposeRepository {
    val remote: RemoteDataSource
}
