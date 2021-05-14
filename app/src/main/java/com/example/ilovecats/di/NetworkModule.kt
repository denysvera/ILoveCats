package com.example.ilovecats.di

import android.app.Application
import com.example.ilovecats.BuildConfig
import com.example.ilovecats.Helpers.Constants
import com.example.ilovecats.network.AuthenticationInterceptor
import com.example.ilovecats.network.ILoveCatsApiService
import com.example.ilovecats.network.ILoveCatsApiServiceHelper
import com.example.ilovecats.network.ILoveCatsApiServiceHelperImpl
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(application: Application, client: OkHttpClient): Retrofit
            = retrofitConfiguration(application, client)

    private fun retrofitConfiguration(
        application: Application,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, authenticationInterceptor: AuthenticationInterceptor): OkHttpClient {
        return OkHttpClient.Builder().writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        else
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            }
    }

    @Provides
    @Singleton
    fun providesAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }

    @Provides
    @Singleton
    fun providesILoveCatsApiService(retrofit: Retrofit): ILoveCatsApiService {
        return retrofit.create(ILoveCatsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideILoveCatsApiServiceHelper(iLoveCatsApiServiceHelper: ILoveCatsApiServiceHelperImpl): ILoveCatsApiServiceHelper= iLoveCatsApiServiceHelper
}