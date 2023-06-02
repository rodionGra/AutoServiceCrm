package com.autoservicecrm.shared.ui

sealed class Event(
    open val name: String
) {
    object Success : Event("Success")
    object Error : Event("Error")
}
