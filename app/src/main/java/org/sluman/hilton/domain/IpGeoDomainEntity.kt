package org.sluman.hilton.domain

data class IpGeoDomainEntity(
    var query       : String? = null,
    var status      : String? = null,
    var country     : String? = null,
    var countryCode : String? = null,
    var region      : String? = null,
    var regionName  : String? = null,
    var city        : String? = null,
    var zip         : String? = null,
    var lat         : Double? = null,
    var lon         : Double? = null,
    var timezone    : String? = null,
    var isp         : String? = null,
    var org         : String? = null,
    var asCode      : String? = null
)
