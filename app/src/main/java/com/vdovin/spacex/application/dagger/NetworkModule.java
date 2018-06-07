package com.vdovin.spacex.application.dagger;

import android.content.Context;

import com.vdovin.spacex.api.SpaceApi;
import com.vdovin.spacex.util.ConnectivityInfo;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.spacexdata.com/";

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
    }

    @Provides
    SpaceApi provideSpaceApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(SpaceApi.class);
    }

    @Provides
    ConnectivityInfo provideConnectivityInfo(Context context) {
        return new ConnectivityInfo(context);
    }

}
