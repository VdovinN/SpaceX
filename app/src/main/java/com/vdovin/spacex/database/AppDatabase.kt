package com.vdovin.spacex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vdovin.spacex.database.dao.SpaceXDao
import com.vdovin.spacex.database.model.SpaceX

@Database(entities = [SpaceX::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spaceXDao(): SpaceXDao
}