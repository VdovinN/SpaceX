package com.vdovin.spacex.application.dagger;

import com.vdovin.spacex.base.SpaceActivity;
import com.vdovin.spacex.screen.detail.LaunchDetailsFragment;
import com.vdovin.spacex.screen.detail.dagger.LaunchDetailsModule;
import com.vdovin.spacex.screen.main.LaunchesFragment;
import com.vdovin.spacex.screen.main.dagger.LaunchesFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract SpaceActivity bindSpaceActivity();

    @ContributesAndroidInjector(modules = {LaunchesFragmentModule.class})
    abstract LaunchesFragment bindLaunchesFragment();

    @ContributesAndroidInjector(modules = {LaunchDetailsModule.class})
    abstract LaunchDetailsFragment bindLaunchDetailsFragment();

}
