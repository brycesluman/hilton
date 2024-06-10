package org.sluman.hilton.domain

interface IpGeoRepository {
    suspend fun getGeo(ip: String?): IpGeoDomainEntity?
}