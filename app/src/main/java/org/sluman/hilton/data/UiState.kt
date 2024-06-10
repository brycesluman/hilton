package org.sluman.hilton.data

import org.sluman.hilton.domain.IpGeoDomainEntity

data class UiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val geoDetail: IpGeoDomainEntity? = null
)
