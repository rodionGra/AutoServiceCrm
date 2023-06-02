package com.autoservicecrm.master.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Master(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
)
