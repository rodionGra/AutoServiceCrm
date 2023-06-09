package com.autoservicecrm.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val customer: Customer
)
