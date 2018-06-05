package com.vdovin.spacex.application.dagger;

import com.vdovin.spacex.SpaceActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract SpaceActivity bindSpaceActivity();
}
