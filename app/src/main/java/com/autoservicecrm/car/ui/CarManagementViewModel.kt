package com.autoservicecrm.car.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autoservicecrm.R
import com.autoservicecrm.car.data.CarRepository
import com.autoservicecrm.car.data.model.PostCarDto
import com.autoservicecrm.car.ui.models.CarScreenStateUiModel
import com.autoservicecrm.shared.ui.Event
import com.autoservicecrm.shared.ui.composable.dialog.models.Field
import com.autoservicecrm.shared.ui.composable.dialog.models.TextFieldsDialogUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarManagementViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CarScreenStateUiModel.getLoadingState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow: Flow<Event>
        get() = _eventChannel.receiveAsFlow()

    init {
        updateCarList()
    }

    private fun updateCarList() {
        viewModelScope.launch {
            val cars = carRepository.getCars()
            val state = cars?.let {
                CarScreenStateUiModel(cars = it)
            } ?: run {
                CarScreenStateUiModel.getErrorState()
            }
            _uiState.emit(state)
        }
    }

    fun getNewCarFields(): TextFieldsDialogUiModel {
        return TextFieldsDialogUiModel(
            R.string.create_car_title,
            R.string.create_car_subtitle,
            listOf(
                Field.TextInput(R.string.car_brand_hint, CAR_BRAND),
                Field.TextInput(R.string.car_model_hint, CAR_MODEL),
                Field.TextInput(R.string.customer_id_hint, CUSTOMER_ID),
            )
        )
    }

    fun addNewCar(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.postNewCar(
                PostCarDto(
                    brand = map[CAR_BRAND] ?: "",
                    model = map[CAR_MODEL] ?: "",
                    customerId = map[CUSTOMER_ID]?.toIntOrNull() ?: -1
                )
            )
            result?.let {
                _eventChannel.send(Event.Success)
                updateCarList()
            } ?: run {
                _eventChannel.send(Event.Error)
            }
        }
    }

    companion object {
        const val CAR_BRAND = "CAR_NAME"
        const val CAR_MODEL = "CAR_MODEL"
        const val CUSTOMER_ID = "CUSTOMER_ID"
    }
}
