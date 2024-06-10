package org.sluman.hilton.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sluman.hilton.R
import org.sluman.hilton.data.UiState
import org.sluman.hilton.presentation.IpGeoViewModel

@Composable
fun MainScreenRoot() {
    Scaffold(topBar = {
        AppBar(
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            val ipGeoViewModel: IpGeoViewModel = hiltViewModel()
            MainScreen(
                onButtonClick = {
                    ipGeoViewModel.getGeo(it)
                },
                modifier = Modifier
                    .fillMaxSize(),
                state = ipGeoViewModel.uiState.collectAsState(),
            )
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier,
               onButtonClick: (query: String?) -> Unit = {},
               state: State<UiState>,) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Domain or IP Address") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password)
    )

    Button(onClick = {
        onButtonClick(text)
        keyboardController?.hide()
    }) {
        Text(text = "Get GEO data")
    }
    Box(modifier = Modifier.fillMaxHeight()) {
        if (state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
        if (state.value.isError) {
            state.value.errorMessage?.let {
                ErrorMessage(
                    message = stringResource(R.string.error),
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
        }
        Column(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            state.value.geoDetail?.query?.let {
                DetailRow(stringResource(id = R.string.details_query), it)
            }
            state.value.geoDetail?.status?.let {
                DetailRow(stringResource(id = R.string.details_status), it)
            }
            state.value.geoDetail?.country?.let {
                DetailRow(stringResource(id = R.string.details_country), it)
            }
            state.value.geoDetail?.countryCode?.let {
                DetailRow(stringResource(id = R.string.details_country_code), it)
            }
            state.value.geoDetail?.region?.let {
                DetailRow(stringResource(id = R.string.details_region), it)
            }
            state.value.geoDetail?.regionName?.let {
                DetailRow(stringResource(id = R.string.details_region_name), it)
            }
            state.value.geoDetail?.city?.let {
                DetailRow(stringResource(id = R.string.details_city), it)
            }
            state.value.geoDetail?.zip?.let {
                DetailRow(stringResource(id = R.string.details_zip), it)
            }
            state.value.geoDetail?.lat?.let {
                DetailRow(stringResource(id = R.string.details_lat), it.toString())
            }
            state.value.geoDetail?.lon?.let {
                DetailRow(stringResource(id = R.string.details_lon), it.toString())
            }
            state.value.geoDetail?.timezone?.let {
                DetailRow(stringResource(id = R.string.details_timezone), it)
            }
            state.value.geoDetail?.isp?.let {
                DetailRow(stringResource(id = R.string.details_isp), it)
            }
            state.value.geoDetail?.org?.let {
                DetailRow(stringResource(id = R.string.details_org), it)
            }
            state.value.geoDetail?.asCode?.let {
                DetailRow(stringResource(id = R.string.details_as_code), it)
            }
        }
    }
}

@Composable
fun DetailRow(key: String, value: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp, bottom = 4.dp)) {
        Text(
            text = key,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(text = value,
            modifier = Modifier.weight(1f))
    }
    HorizontalDivider(thickness = .5.dp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Row() {
                Image(
                    painter = painterResource(
                        R.drawable.hilton_logo
                    ),
                    contentDescription = "logo",
                    modifier = Modifier.size(72.dp)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun ErrorMessage(message: String, modifier: Modifier) {
    Text(text = message,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier.padding(16.dp)
    )
}