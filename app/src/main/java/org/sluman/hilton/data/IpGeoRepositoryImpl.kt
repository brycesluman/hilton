package org.sluman.hilton.data

import org.sluman.hilton.database.GeoDao
import org.sluman.hilton.domain.IpGeoDomainEntity
import org.sluman.hilton.domain.IpGeoRepository
import org.sluman.hilton.data.network.ApiClient

class IpGeoRepositoryImpl(private val apiClient: ApiClient,
                          private val dao: GeoDao
): IpGeoRepository {
    override suspend fun getGeo(searchStr: String?): IpGeoDomainEntity? {
        val searchQuery = if (searchStr.isNullOrEmpty()) "emptySearch" else searchStr

        val cacheResp = dao.get(searchQuery)
        if (cacheResp == null) {
            getRemote(searchStr, searchQuery)
        } else if (cacheResp.timestamp < System.currentTimeMillis() + TTL_MS) {
            dao.delete(searchQuery)
            getRemote(searchStr, searchQuery)
        }
        return dao.get(searchQuery)?.toDomain()
    }

    private suspend fun getRemote(searchStr: String?, searchQuery: String) {
        apiClient.apiService.getIpGeoByIp(searchStr).toCache(searchQuery)?.let { item ->
            dao.insert(item)
        }
    }

    companion object {
        const val TTL_MS = 300000
    }
}