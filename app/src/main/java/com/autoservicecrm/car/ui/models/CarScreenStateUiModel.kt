package com.autoservicecrm.car.ui.models

import com.autoservicecrm.car.data.model.Car

data class CarScreenStateUiModel(
    val isLoading: Boolean = false,
    val cars: List<Car>? = null
) {
    companion object {
        fun getLoadingState() = CarScreenStateUiModel(true, null)
    }
}
