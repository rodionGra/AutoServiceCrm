package com.autoservicecrm.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: Int,
    val name: String,
    val lastname: String,
    val surname: String,
    val phone: String
)
