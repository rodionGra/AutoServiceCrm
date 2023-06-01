package com.autoservicecrm.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autoservicecrm.R
import com.autoservicecrm.home.ui.models.FeatureUiModel
import com.autoservicecrm.home.ui.models.HomeUiModel
import com.autoservicecrm.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiModel.getLoadingState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
           _uiState.emit(HomeUiModel(isLoading = false, getFeaturesUiModels()))
        }
    }

    private fun getFeaturesUiModels(): List<FeatureUiModel> {
        return listOf(
            FeatureUiModel(R.string.car_management_title, Screen.CarManagement)
        )
    }
}
