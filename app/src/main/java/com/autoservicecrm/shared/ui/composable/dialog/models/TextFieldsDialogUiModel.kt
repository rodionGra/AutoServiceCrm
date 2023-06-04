package com.autoservicecrm.shared.ui.composable.dialog.models

import androidx.annotation.StringRes

data class TextFieldsDialogUiModel(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val fields: List<Field>
)
