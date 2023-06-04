package com.autoservicecrm.order.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val startDate: String,
    val endDate: String,
    val description: String,
    val price: Double,
    val tuningBox: TuningBox,
    val isDone: Boolean
)

@Serializable
data class TuningBox(
    val boxNumber: Int
)
