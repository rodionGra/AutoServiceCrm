package com.autoservicecrm.car.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autoservicecrm.car.data.CarRepository
import com.autoservicecrm.car.ui.models.CarScreenStateUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarManagementViewModel @Inject constructor(
    carRepository: CarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CarScreenStateUiModel.getLoadingState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                CarScreenStateUiModel(cars = carRepository.getCars())
            )
        }
    }
}
