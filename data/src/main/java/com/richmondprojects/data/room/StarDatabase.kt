package com.richmondprojects.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.richmondprojects.domain.model.Results

@Database(
    entities = [Results::class, ResultRemoteKey::class],
    version = 1, exportSchema = false
)

abstract class StarDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): StarDatabase {
            return Room.databaseBuilder(
                context,
                StarDatabase::class.java,
                "star_database"
            ).build()
        }
    }

    abstract fun getResultDao(): ResultsDao
}