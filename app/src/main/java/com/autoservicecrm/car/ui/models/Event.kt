package com.autoservicecrm.car.ui.models

sealed class Event(
    open val name: String
) {
    object Success : Event("Success")
    object Error : Event("Error")
}
