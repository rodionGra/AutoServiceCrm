package com.autoservicecrm.order.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostOrderDto(
    val carId: Int,
    val description: String,
    val done: Boolean,
    val endDate: String,
    val masterId: Int,
    val price: Int,
    val startDate: String,
    val tuningBoxNumber: Int
)
