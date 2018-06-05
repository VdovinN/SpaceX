package com.vdovin.spacex.application.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vdovin.spacex.rx.AppRxSchedulers;
import com.vdovin.spacex.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    Context provideContext() {
        return appContext;
    }

    @Provides
    RxSchedulers provideSchedulers() {
        return new AppRxSchedulers();
    }

}
