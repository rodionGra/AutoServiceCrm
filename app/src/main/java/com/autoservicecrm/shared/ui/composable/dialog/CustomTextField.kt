package com.autoservicecrm.shared.ui.composable.dialog

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.autoservicecrm.shared.ui.composable.dialog.models.Field

@Composable
fun CustomTextField(
    textInput: Field.TextInput,
    mapTextFields: MutableState<Map<String, String>>
) {
    val map = mapTextFields.value.toMutableMap()
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    val value = mapTextFields.value[textInput.key] ?: return
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            mapTextFields.value = map.apply {
                put(textInput.key, it)
            }
        },
        label = { Text(stringResource(textInput.hint)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = textInput.keyboardType),
    )
}
