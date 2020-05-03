package com.vdovin.spacex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vdovin.spacex.database.model.SpaceX

@Dao
interface SpaceXDao {
    @Query("SELECT * FROM space_x")
    fun getAllLaunches() : List<SpaceX>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg spaceX: SpaceX)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spaceXList: List<SpaceX>)

    @Query("SELECT * FROM space_x WHERE flightNumber=:flightNumber")
    fun getLaunchByFlightNumber(flightNumber: Long): SpaceX
}