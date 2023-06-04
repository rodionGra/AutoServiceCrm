package com.autoservicecrm.order.ui.models

import com.autoservicecrm.order.data.model.Order

data class OrderScreenStateUiModel(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val orders: List<Order>? = null
) {
    companion object {
        fun getLoadingState() = OrderScreenStateUiModel(isLoading = true, orders = null)

        fun getErrorState() = OrderScreenStateUiModel(isError = true, orders = null)
    }
}
