package com.vdovin.spacex.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.database.model.SpaceX;

@Database(entities = {SpaceX.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "space_x_db.db";

    public static AppDatabase initDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                DB_NAME).build();
    }

    public abstract SpaceXDao spaceXDao();

}
