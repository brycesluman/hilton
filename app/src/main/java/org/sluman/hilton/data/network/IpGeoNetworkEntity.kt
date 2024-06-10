package org.sluman.hilton.data.network

import com.google.gson.annotations.SerializedName

data class IpGeoNetworkEntity(
    @SerializedName("query"       ) var query       : String? = null,
    @SerializedName("status"      ) var status      : String? = null,
    @SerializedName("country"     ) var country     : String? = null,
    @SerializedName("countryCode" ) var countryCode : String? = null,
    @SerializedName("region"      ) var region      : String? = null,
    @SerializedName("regionName"  ) var regionName  : String? = null,
    @SerializedName("city"        ) var city        : String? = null,
    @SerializedName("zip"         ) var zip         : String? = null,
    @SerializedName("lat"         ) var lat         : Double? = null,
    @SerializedName("lon"         ) var lon         : Double? = null,
    @SerializedName("timezone"    ) var timezone    : String? = null,
    @SerializedName("isp"         ) var isp         : String? = null,
    @SerializedName("org"         ) var org         : String? = null,
    @SerializedName("as"          ) var asCode      : String? = null
)
