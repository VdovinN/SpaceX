package com.vdovin.spacex.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vdovin.spacex.database.model.SpaceX;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface SpaceXDao {

    @Query("SELECT * FROM space_x")
    Flowable<List<SpaceX>> getAllLaunches();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SpaceX... spaceX);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SpaceX> spaceXList);
}
