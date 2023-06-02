package com.autoservicecrm.master.ui.models

import com.autoservicecrm.master.data.model.Master

data class MasterScreenStateUiModel(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val masters: List<Master>? = null
) {
    companion object {
        fun getLoadingState() = MasterScreenStateUiModel(isLoading = true, masters = null)

        fun getErrorState() = MasterScreenStateUiModel(isError = true, masters = null)
    }
}
