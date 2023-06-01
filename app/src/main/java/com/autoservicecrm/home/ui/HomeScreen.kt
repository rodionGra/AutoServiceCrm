package com.autoservicecrm.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.autoservicecrm.R
import com.autoservicecrm.home.ui.composable.FeaturesLazyListView

@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.home_screen_topbar_title)) },
                    backgroundColor = MaterialTheme.colors.primary,
                )
            }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                when {
                    uiState.isLoading == true -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }

                    !uiState.features.isNullOrEmpty() -> {
                        FeaturesLazyListView(features = uiState.features!!) { screen ->
                            navController.navigate(screen.route)
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), hiltViewModel())
}
