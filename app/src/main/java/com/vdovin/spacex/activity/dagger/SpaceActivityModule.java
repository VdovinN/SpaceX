package com.vdovin.spacex.activity.dagger;

import com.vdovin.spacex.activity.structure.SpacePresenter;
import com.vdovin.spacex.api.SpaceApi;
import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.rx.RxSchedulers;
import com.vdovin.spacex.util.ConnectivityInfo;

import dagger.Module;
import dagger.Provides;

@Module
public class SpaceActivityModule {

    @Provides
    SpacePresenter providePresenter(SpaceApi api, SpaceXDao dao, ConnectivityInfo connectivityInfo, RxSchedulers rxSchedulers) {
        return new SpacePresenter(api, dao, connectivityInfo, rxSchedulers);
    }

}
