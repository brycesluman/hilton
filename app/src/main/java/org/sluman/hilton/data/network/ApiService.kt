package org.sluman.hilton.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("json/{searchStr}")
    suspend fun getIpGeoByIp(@Path("searchStr") searchStr: String?): IpGeoNetworkEntity
}