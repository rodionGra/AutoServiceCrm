package com.autoservicecrm.car.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.autoservicecrm.R
import com.autoservicecrm.car.ui.views.CarsLazyListView
import com.autoservicecrm.shared.ui.theme.White

@Composable
fun CarManagementScreen(
    viewModel: CarManagementViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        val isScrollingUp = remember {
            mutableStateOf(true)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.car_management_title)) },
                    backgroundColor = MaterialTheme.colors.primary,
                )
            },
            floatingActionButton = {
                if (isScrollingUp.value) {
                    FloatingActionButton(
                        onClick = {},
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(Icons.Filled.Add, tint = White, contentDescription = null)
                    }
                }
            }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }

                    uiState.cars.isNullOrEmpty().not() -> {
                        CarsLazyListView(uiState.cars!!, isScrollingUp)
                    }

                    else -> {}
                }

            }
        }
    }
}

@Preview
@Composable
private fun CarManagementScreenPreview() {
    CarManagementScreen(hiltViewModel())
}
