package com.vdovin.spacex.di

import androidx.room.Room
import com.vdovin.spacex.api.SpaceApi
import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.database.AppDatabase
import com.vdovin.spacex.main.MainActivity
import com.vdovin.spacex.main.MainPresenter
import com.vdovin.spacex.main.MainView
import com.vdovin.spacex.screen.details.LaunchDetailsFragment
import com.vdovin.spacex.screen.details.LaunchDetailsPresenter
import com.vdovin.spacex.screen.details.LaunchDetailsView
import com.vdovin.spacex.screen.main.LaunchesFragment
import com.vdovin.spacex.screen.main.LaunchesPresenter
import com.vdovin.spacex.screen.main.LaunchesView
import com.vdovin.spacex.util.ConnectionHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.spacexdata.com/"
private const val DB_NAME = "space_x_db.db"

val networkModule = module {
    factory { HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .apply { level = HttpLoggingInterceptor.Level.BODY } }
    factory { provideHttpClient(get()) }
    factory { provideSpaceApi(get()) }
    single { provideRetrofit(get()) }
    factory { ConnectionHelper(androidApplication()) }
}

val databaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().spaceXDao() }
}

val screenModule = module {
    factory<BasePresenter<MainView>>(named(MainActivity.TAG)) { MainPresenter(get(), get(), get()) }
    factory<BasePresenter<LaunchesView>>(named(LaunchesFragment.TAG)) { LaunchesPresenter(get()) }
    factory<BasePresenter<LaunchDetailsView>>(named(LaunchDetailsFragment.TAG)) { LaunchDetailsPresenter(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

fun provideSpaceApi(retrofit: Retrofit): SpaceApi = retrofit.create(SpaceApi::class.java)