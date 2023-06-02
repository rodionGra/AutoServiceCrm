package com.autoservicecrm.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostCarDto(
    val brand: String,
    val model: String,
    val customerId: Int
)
