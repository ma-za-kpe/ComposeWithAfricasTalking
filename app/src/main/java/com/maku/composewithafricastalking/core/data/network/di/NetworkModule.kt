package com.maku.composewithafricastalking.core.data.network.di

import com.maku.composewithafricastalking.core.data.network.client.ATApiClient
import com.maku.composewithafricastalking.core.data.network.client.LoggingInterceptor
import com.maku.composewithafricastalking.core.util.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val ongoing: Request.Builder = chain.request().newBuilder()
                    ongoing.addHeader(
                        "Accept",
                        "application/json"
                    )
                    ongoing.addHeader(
                        "Content-Type",
                        "application/x-www-form-urlencoded"
                    )
                    chain.proceed(ongoing.build())
                }
            )
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(160, TimeUnit.SECONDS)
            .readTimeout(160, TimeUnit.SECONDS)
            .writeTimeout(160, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(
        loggingInterceptor: LoggingInterceptor
    ): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ATApiClient {
        return retrofit.create(ATApiClient::class.java)
    }
}
