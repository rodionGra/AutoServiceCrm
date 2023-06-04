@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.autoservicecrm.shared.ui.composable.dialog

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.shared.ui.composable.dialog.models.Field
import com.autoservicecrm.shared.ui.composable.dialog.models.TextFieldsDialogUiModel
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

    LaunchedEffect(textFieldsDialogUiModel) {
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
                    text = stringResource(textFieldsDialogUiModel.title),
                    style = MaterialTheme.typography.h6
                )
                Icon(Icons.Filled.Close, contentDescription = null)
            }
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(textFieldsDialogUiModel.subtitle),
                style = MaterialTheme.typography.body2
            )
            LazyColumn {
                items(textFieldsDialogUiModel.fields) {
                    when (it) {
                        is Field.TextInput -> {
                            CustomTextField(it, mapTextFields)
                        }

                        is Field.DateInput -> {
                            CustomDataPicker(it, mapTextFields)
                        }
                    }
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
                    text = stringResource(R.string.confirm),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun TextFieldsDialogPreview() {
    TextFieldsDialog(
        textFieldsDialogUiModel = TextFieldsDialogUiModel(
            R.string.create_car_title,
            R.string.confirm,
            listOf(
                Field.TextInput(R.string.car_model_hint, "car_model"),
                Field.DateInput("DateInput"),
            )
        ), onSubmitClick = {}
    )
}

