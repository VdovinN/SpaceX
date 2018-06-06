package com.vdovin.spacex.screen.main.dagger;

import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.rx.RxSchedulers;
import com.vdovin.spacex.screen.main.structure.LaunchesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LaunchesFragmentModule {

    @Provides
    LaunchesPresenter providePresenter(SpaceXDao spaceXDao, RxSchedulers rxSchedulers) {
        return new LaunchesPresenter(spaceXDao, rxSchedulers);
    }

}
