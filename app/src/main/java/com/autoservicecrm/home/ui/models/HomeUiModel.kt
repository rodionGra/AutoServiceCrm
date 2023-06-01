package com.autoservicecrm.home.ui.models

import androidx.annotation.StringRes
import com.autoservicecrm.navigation.Screen

data class HomeUiModel(
    val isLoading: Boolean? = null,
    val features: List<FeatureUiModel>? = null
) {
    companion object {
        fun getLoadingState() = HomeUiModel(isLoading = true, features = null)
    }
}

data class FeatureUiModel(
    @StringRes
    val title: Int,
    val navigateTo: Screen
)
