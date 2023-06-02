package com.autoservicecrm.master.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostMasterDto(
    val name: String,
    val surname: String,
    val phone: String
)
