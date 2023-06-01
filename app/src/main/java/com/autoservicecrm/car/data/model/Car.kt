package com.autoservicecrm.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val customer: Customer
)

@Serializable
data class Customer(
    val id: Int,
    val name: String,
    val lastname: String,
    val surname: String,
    val phone: String
)
