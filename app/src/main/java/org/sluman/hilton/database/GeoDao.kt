package org.sluman.hilton.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GeoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(geoEntity: GeoCacheEntity): Long

    @Query("SELECT * FROM geoCache WHERE searchStr=:searchStr")
    suspend fun get(searchStr: String): GeoCacheEntity?

    @Query("DELETE FROM geoCache WHERE searchStr=:searchStr")
    suspend fun delete(searchStr: String)
}