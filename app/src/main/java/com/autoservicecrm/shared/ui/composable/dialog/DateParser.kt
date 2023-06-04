package com.autoservicecrm.shared.ui.composable.dialog

import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api

private const val DELIMITER = '/'

@OptIn(ExperimentalMaterial3Api::class)
fun DateRangePickerState.toCustomString(): String {
    return "${this.selectedStartDateMillis}$DELIMITER${this.selectedEndDateMillis}"
}

fun String.toDateRange(): Pair<Long?, Long?> {
    return Pair(
        this.substringBefore(DELIMITER).toLongOrNull(),
        this.substringAfter(DELIMITER).toLongOrNull()
    )
}

