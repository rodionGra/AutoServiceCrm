package com.autoservicecrm.car.ui.views

import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.shared.ui.theme.BlueLight
import com.autoservicecrm.shared.ui.theme.White

@Composable
fun TextFieldsDialog(
    modifier: Modifier = Modifier,
    textFieldsDialogUiModel: TextFieldsDialogUiModel,
    onSubmitClick: (Map<String, String>) -> Unit
) {
    val mapTextFields = remember {
        mutableStateOf(mapOf<String, String>())
    }

    LaunchedEffect(Unit) {
        mapTextFields.value = textFieldsDialogUiModel.fields.associate {
            it.key to ""
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .scrollable(rememberScrollState(), Orientation.Vertical),
        backgroundColor = White,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.create_car_title),
                    style = MaterialTheme.typography.h6
                )
                Icon(Icons.Filled.Close, contentDescription = null)
            }
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Опишіть новий автомобіль та вкажіть id власника",
                style = MaterialTheme.typography.body2
            )
            LazyColumn {
                items(textFieldsDialogUiModel.fields) {
                    CustomTextField(it, mapTextFields)
                }
            }
            Button(
                onClick = {
                    onSubmitClick(mapTextFields.value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BlueLight
                )
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.create_car),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    field: Field,
    mapTextFields: MutableState<Map<String, String>>
) {
    val map = mapTextFields.value.toMutableMap()
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    TextField(
        value = mapTextFields.value[field.key] ?: return,
        onValueChange = {
            mapTextFields.value = map.apply {
                put(field.key, it)
            }
        },
        label = { Text(stringResource(field.hint)) },
        modifier = Modifier.fillMaxWidth()
    )
}

data class TextFieldsDialogUiModel(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val fields: List<Field>
)

data class Field(
    val hint: Int,
    val key: String
)

@Preview
@Composable
fun TextFieldsDialogPreview() {
    TextFieldsDialog(
        textFieldsDialogUiModel = TextFieldsDialogUiModel(
            R.string.create_car_title,
            R.string.create_car,
            listOf(
                Field(R.string.car_model_hint, "car_model")
            )
        ), onSubmitClick = {}
    )
}

