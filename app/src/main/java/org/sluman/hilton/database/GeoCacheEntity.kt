package org.sluman.hilton.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="geoCache")
data class GeoCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "searchStr")
    var searchStr: String,

    @ColumnInfo(name= "queryStr")
    var queryStr: String?,

    @ColumnInfo(name= "status")
    var status: String?,

    @ColumnInfo(name= "country")
    var country: String?,

    @ColumnInfo(name= "countryCode")
    var countryCode: String?,

    @ColumnInfo(name= "region")
    var region: String?,

    @ColumnInfo(name= "regionName")
    var regionName: String?,

    @ColumnInfo(name= "city")
    var city: String?,

    @ColumnInfo(name= "zip")
    var zip: String?,

    @ColumnInfo(name= "lat")
    var lat: Double?,

    @ColumnInfo(name= "lon")
    var lon: Double?,

    @ColumnInfo(name= "timezone")
    var timezone: String?,

    @ColumnInfo(name= "isp")
    var isp: String?,

    @ColumnInfo(name= "org")
    var org: String?,

    @ColumnInfo(name= "asCode")
    var asCode: String?,

    @ColumnInfo(name= "timestamp")
    var timestamp: Long,
)
