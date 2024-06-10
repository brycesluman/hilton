package org.sluman.hilton.data

import org.sluman.hilton.data.network.IpGeoNetworkEntity
import org.sluman.hilton.database.GeoCacheEntity
import org.sluman.hilton.domain.IpGeoDomainEntity

fun IpGeoNetworkEntity.toCache(searchStr: String): GeoCacheEntity? {
    return GeoCacheEntity(
        searchStr = searchStr,
        queryStr = query,
        asCode = asCode,
        city = city,
        country = country,
        countryCode = countryCode,
        isp = isp,
        lat = lat,
        lon = lon,
        org = org,
        region = region,
        regionName = regionName,
        status = status,
        timezone = timezone,
        zip = zip,
        timestamp = System.currentTimeMillis()
    )
}
fun GeoCacheEntity.toDomain(): IpGeoDomainEntity {
    return IpGeoDomainEntity(
        query = queryStr,
        asCode = asCode,
        city = city,
        country = country,
        countryCode = countryCode,
        isp = isp,
        lat = lat,
        lon = lon,
        org = org,
        region = region,
        regionName = regionName,
        status = status,
        timezone = timezone,
        zip = zip
    )
}