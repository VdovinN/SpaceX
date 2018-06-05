package com.vdovin.spacex.application.dagger;

import com.vdovin.spacex.application.SpaceApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        BuildersModule.class
})
public interface AppComponent {

    void inject(SpaceApplication application);

}
