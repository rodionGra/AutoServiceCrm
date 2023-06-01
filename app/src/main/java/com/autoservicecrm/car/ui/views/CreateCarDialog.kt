package com.autoservicecrm.car.ui.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.shared.ui.theme.BlueLight
import com.autoservicecrm.shared.ui.theme.White

@Composable
fun CreateCarDialog() {
    val textField1Value = remember { mutableStateOf(TextFieldValue()) }
    val textField2Value = remember { mutableStateOf(TextFieldValue()) }
    val textField3Value = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
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
                text = stringResource(R.string.create_car_subtitle),
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            TextField(
                value = textField1Value.value,
                onValueChange = { textField1Value.value = it },
                label = { Text("Field 1") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            TextField(
                value = textField2Value.value,
                onValueChange = { textField2Value.value = it },
                label = { Text("Field 2") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            TextField(
                value = textField3Value.value,
                onValueChange = { textField3Value.value = it },
                label = { Text("Field 3") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = {
                    // Обробка кнопки "Підтвердити"
                    val field1Text = textField1Value.value.text
                    val field2Text = textField2Value.value.text
                    val field3Text = textField3Value.value.text
                    // Додайте вашу логіку для обробки введених даних
                    // Наприклад, виведення їх на консоль
                    println("Field 1: $field1Text")
                    println("Field 2: $field2Text")
                    println("Field 3: $field3Text")
                    // Закриття діалогу
                    //context.popBackStack()
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

@Preview
@Composable
fun CreateCarDialogPreview() {
    CreateCarDialog()
}

