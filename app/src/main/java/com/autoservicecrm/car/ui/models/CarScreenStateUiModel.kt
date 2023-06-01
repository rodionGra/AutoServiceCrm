package com.autoservicecrm.car.ui.models

import com.autoservicecrm.car.data.model.Car

data class CarScreenStateUiModel(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cars: List<Car>? = null
) {
    companion object {
        fun getLoadingState() = CarScreenStateUiModel(isLoading = true, cars = null)

        fun getErrorState() = CarScreenStateUiModel(isError = true, cars = null)
    }
}
