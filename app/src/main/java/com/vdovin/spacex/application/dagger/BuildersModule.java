package com.vdovin.spacex.application.dagger;

import com.vdovin.spacex.activity.SpaceActivity;
import com.vdovin.spacex.activity.dagger.SpaceActivityModule;
import com.vdovin.spacex.screen.detail.LaunchDetailsFragment;
import com.vdovin.spacex.screen.detail.dagger.LaunchDetailsModule;
import com.vdovin.spacex.screen.main.LaunchesFragment;
import com.vdovin.spacex.screen.main.dagger.LaunchesFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {SpaceActivityModule.class})
    abstract SpaceActivity bindSpaceActivity();

    @ContributesAndroidInjector(modules = {LaunchesFragmentModule.class})
    abstract LaunchesFragment bindLaunchesFragment();

    @ContributesAndroidInjector(modules = {LaunchDetailsModule.class})
    abstract LaunchDetailsFragment bindLaunchDetailsFragment();

}
