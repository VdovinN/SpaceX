package com.vdovin.spacex.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vdovin.spacex.database.model.SpaceX;

import java.util.List;

@Dao
public interface SpaceXDao {

    @Query("SELECT * FROM space_x")
    List<SpaceX> getAllLaunches();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SpaceX... spaceX);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SpaceX> spaceXList);
}
