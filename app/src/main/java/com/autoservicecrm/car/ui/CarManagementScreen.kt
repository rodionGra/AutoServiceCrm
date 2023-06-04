package com.autoservicecrm.car.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.autoservicecrm.car.ui.views.CarsLazyListView
import com.autoservicecrm.shared.ui.composable.dialog.TextFieldsDialog
import com.autoservicecrm.shared.ui.Event
import com.autoservicecrm.shared.ui.composable.ErrorView
import com.autoservicecrm.shared.ui.composable.TopBar
import com.autoservicecrm.shared.ui.theme.RedErrorLight
import com.autoservicecrm.shared.ui.theme.White

@Composable
fun CarManagementScreen(
    viewModel: CarManagementViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = Unit) {
        viewModel.eventsFlow.collect {
            when (it) {
                Event.Error -> {
                    scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = "",
                            duration = SnackbarDuration.Short,
                            actionLabel = it.name
                        )
                }

                Event.Success -> {
                    scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = "",
                            duration = SnackbarDuration.Short,
                            actionLabel = it.name
                        )
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        val isScrollingUp = remember {
            mutableStateOf(true)
        }

        val showDialog = remember {
            mutableStateOf(false)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar() },
            floatingActionButton = {
                if (isScrollingUp.value) {
                    FloatingActionButton(
                        onClick = { showDialog.value = true }
                    ) {
                        Icon(Icons.Filled.Add, tint = White, contentDescription = null)
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = it) { data ->
                    val color = when (data.actionLabel) {
                        Event.Success.name -> Color(0xFF82CD47)
                        Event.Error.name -> RedErrorLight
                        else -> MaterialTheme.colors.primary
                    }
                    Snackbar(
                        snackbarData = data,
                        backgroundColor = color,
                        actionColor = White
                    )
                }
            }
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }

                    uiState.cars.isNullOrEmpty().not() -> {
                        CarsLazyListView(uiState.cars!!, isScrollingUp)
                    }

                    uiState.isError -> {
                        ErrorView()
                    }

                    else -> {}
                }

                if (showDialog.value) {
                    Dialog(
                        onDismissRequest = { showDialog.value = false },
                        properties = DialogProperties(
                            dismissOnBackPress = true,
                            usePlatformDefaultWidth = false
                        ),
                    ) {
                        TextFieldsDialog(
                            textFieldsDialogUiModel = viewModel.getNewCarFields()
                        ) {
                            viewModel.addNewCar(it)
                            showDialog.value = false
                        }
                    }
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
