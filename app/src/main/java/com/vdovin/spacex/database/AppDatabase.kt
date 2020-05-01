package com.vdovin.spacex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vdovin.spacex.database.dao.SpaceXDao
import com.vdovin.spacex.database.model.SpaceX

@Database(entities = [SpaceX::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "space_x_db.db"
        fun initDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME).build()
        }
    }
}