package com.vdovin.spacex.di

import com.vdovin.spacex.api.SpaceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.spacexdata.com/"

val networkModule = module {
    factory { HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT) }
    factory { provideHttpClient(get()) }
    factory { provideSpaceApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

fun provideSpaceApi(retrofit: Retrofit) = retrofit.create(SpaceApi::class.java)