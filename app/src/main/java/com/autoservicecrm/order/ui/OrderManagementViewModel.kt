package com.autoservicecrm.order.ui

import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autoservicecrm.R
import com.autoservicecrm.order.data.OrderRepository
import com.autoservicecrm.order.data.model.Order
import com.autoservicecrm.order.data.model.PostOrderDto
import com.autoservicecrm.order.ui.models.OrderScreenStateUiModel
import com.autoservicecrm.shared.ui.Event
import com.autoservicecrm.shared.ui.composable.dialog.models.Field
import com.autoservicecrm.shared.ui.composable.dialog.models.TextFieldsDialogUiModel
import com.autoservicecrm.shared.ui.composable.dialog.toDateRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class OrderManagementViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderScreenStateUiModel.getLoadingState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow: Flow<Event>
        get() = _eventChannel.receiveAsFlow()

    init {
        updateOrderList()
    }

    private fun updateOrderList() {
        viewModelScope.launch {
            val orders = orderRepository.getAllOrders()
            val state = orders?.let {
                OrderScreenStateUiModel(orders = it)
            } ?: run {
                OrderScreenStateUiModel.getErrorState()
            }
            _uiState.emit(state)
        }
    }

    fun getNewOrderFields(): TextFieldsDialogUiModel {
        return TextFieldsDialogUiModel(
            R.string.create_order_title,
            R.string.create_order_subtitle,
            listOf(
                Field.TextInput(R.string.order_description_hint, ORDER_DESCRIPTION),
                Field.TextInput(R.string.order_price_hint, ORDER_PRICE, KeyboardType.Number),
                Field.TextInput(
                    R.string.order_tuning_box_id_hint, ORDER_BOX_ID, KeyboardType.Number
                ),
                Field.TextInput(R.string.car_id, CAR_ID, KeyboardType.Number),
                Field.TextInput(R.string.master_id_hint, MASTER_ID, KeyboardType.Number),
                Field.DateInput(DATA_PICKER),
            )
        )
    }

    fun addNewOrder(map: Map<String, String>) {
        val startDate = map[DATA_PICKER]?.toDateRange()?.first?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }

        val endDate = map[DATA_PICKER]?.toDateRange()?.second?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }

        viewModelScope.launch {
            val result = orderRepository.postNewOrder(
                PostOrderDto(
                    carId = map[CAR_ID]?.toIntOrNull() ?: 0,
                    description = map[ORDER_DESCRIPTION] ?: "",
                    done = false,
                    endDate = endDate.toString(),
                    masterId = map[MASTER_ID]?.toIntOrNull() ?: 0,
                    price = map[ORDER_PRICE]?.toIntOrNull() ?: 0,
                    startDate = startDate.toString(),
                    tuningBoxNumber = map[ORDER_BOX_ID]?.toIntOrNull() ?: 0
                )
            )
            result?.let {
                _eventChannel.send(Event.Success)
                updateOrderList()
            } ?: run {
                _eventChannel.send(Event.Error)
            }
        }
    }

    fun removeOrder(order: Order) {
        viewModelScope.launch {
            val result = orderRepository.deleteOrder(order.id)
            result?.let {
                _eventChannel.send(Event.Success)
            } ?: run {
                _eventChannel.send(Event.Error)
            }
            updateOrderList()
        }
    }

    companion object {
        const val ORDER_DESCRIPTION = "ORDER_DESCRIPTION"
        const val ORDER_PRICE = "ORDER_PRICE"
        const val ORDER_BOX_ID = "ORDER_BOX_ID"
        const val CAR_ID = "CAR_ID"
        const val MASTER_ID = "MASTER_ID"
        const val DATA_PICKER = "DATA_PICKER"
    }
}



