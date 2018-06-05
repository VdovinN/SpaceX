package com.vdovin.spacex.application.dagger;

import android.content.Context;

import com.vdovin.spacex.database.AppDatabase;
import com.vdovin.spacex.database.dao.SpaceXDao;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.initDatabase(context);
    }

    @Provides
    SpaceXDao provideSpaceXDao(AppDatabase appDatabase) {
        return appDatabase.spaceXDao();
    }


}
