package com.vdovin.spacex.screen.detail.dagger;

import com.vdovin.spacex.screen.detail.structure.LaunchDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LaunchDetailsModule {

    @Provides
    LaunchDetailsPresenter providePresenter() {
        return new LaunchDetailsPresenter();
    }

}
