package org.sluman.hilton.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GeoCacheEntity::class], exportSchema = false, version = 1)
abstract class GeoDatabase: RoomDatabase() {

    abstract fun geoDao(): GeoDao

    companion object {
        const val DATABASE_NAME: String = "geo_db"
    }
}