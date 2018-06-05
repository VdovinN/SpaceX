package com.vdovin.spacex.application;

import android.app.Activity;
import android.app.Application;

import com.vdovin.spacex.application.dagger.AppComponent;
import com.vdovin.spacex.application.dagger.AppModule;
import com.vdovin.spacex.application.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class SpaceApplication extends Application implements HasActivityInjector {

    private static AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
