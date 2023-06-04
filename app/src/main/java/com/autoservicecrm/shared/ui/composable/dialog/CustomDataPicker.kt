@file:OptIn(ExperimentalMaterial3Api::class)

package com.autoservicecrm.shared.ui.composable.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.autoservicecrm.shared.ui.composable.dialog.models.Field

@Composable
fun CustomDataPicker(
    dateInput: Field.DateInput,
    mapTextFields: MutableState<Map<String, String>>
) {
    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = 1685908479858,
        initialSelectedEndDateMillis = 1685908479858,
        initialDisplayMode = DisplayMode.Input
    )

    LaunchedEffect(
        key1 = datePickerState.selectedStartDateMillis,
        key2 = datePickerState.selectedEndDateMillis,
        block = {
            mapTextFields.value = mapTextFields.value.toMutableMap().apply {
                put(dateInput.key, datePickerState.toCustomString())
            }
        }
    )


    DateRangePicker(
        modifier = Modifier.fillMaxWidth(),
        state = datePickerState,
        showModeToggle = false,
        title = null,
        headline = null
    )
}
