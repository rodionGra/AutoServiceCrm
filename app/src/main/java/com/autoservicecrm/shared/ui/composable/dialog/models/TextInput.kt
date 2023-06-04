package com.autoservicecrm.shared.ui.composable.dialog.models

import androidx.compose.ui.text.input.KeyboardType

sealed class Field(
    open val key: String
) {
    data class TextInput(
        val hint: Int,
        override val key: String,
        val keyboardType: KeyboardType = KeyboardType.Text
    ) : Field(key)

    data class DateInput(
        override val key: String
    ) : Field(key)
}

