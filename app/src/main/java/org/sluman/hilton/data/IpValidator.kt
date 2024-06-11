package org.sluman.hilton.data

import android.util.Patterns
import org.sluman.hilton.domain.PatternValidator

class IpValidator(): PatternValidator {
    override fun matchesIpV4(value: String): Boolean {
        return Patterns.IP_ADDRESS.matcher(value).matches()
    }

    override fun matchesDomain(value: String): Boolean {
        return Patterns.DOMAIN_NAME.matcher(value).matches()
    }
}