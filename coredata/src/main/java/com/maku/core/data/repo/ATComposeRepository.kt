package com.maku.core.data.repo

// Abstractions for the different data sources, or services used in your app
interface ATComposeRepository {
    val remote: RemoteDataSource
}