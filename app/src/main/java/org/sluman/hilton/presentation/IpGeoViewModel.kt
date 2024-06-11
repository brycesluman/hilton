package org.sluman.hilton.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.hilton.data.UiState
import org.sluman.hilton.domain.IpGeoRepository
import org.sluman.hilton.domain.PatternValidator
import javax.inject.Inject

@HiltViewModel
class IpGeoViewModel @Inject constructor(
    private val repository: IpGeoRepository,
    private val ipValidator: PatternValidator
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getGeo(query: String?) {
        println(!validInput(query))
        if (!validInput(query)) {
            _uiState.value = uiState.value.copy(
                isError = true,
                errorMessage = "Valid inputs are: \n domain.com \n 10.0.0.1 \n empty input",
                isLoading = false,
                geoDetail = null
            )
            return
        }
        _uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val geo = repository.getGeo(query)
                println(geo)
                _uiState.value = uiState.value.copy(
                    isError = false,
                    isLoading = false,
                    geoDetail = geo
                )
            } catch (e: Exception) {
                println("error caught: $e")
                _uiState.value = uiState.value.copy(
                    isError = true,
                    errorMessage = "An error occurred",
                    isLoading = false
                )
            }
        }

    }

    private fun validInput(query: String?): Boolean {
        return (query.isNullOrEmpty()
                || (ipValidator.matchesIpV4(query)
                || ipValidator.matchesDomain(query)))

    }
}

