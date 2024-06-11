package org.sluman.hilton.domain

interface PatternValidator {
    fun matchesIpV4(value: String): Boolean
    fun matchesDomain(value: String): Boolean
}