package com.autoservicecrm.car.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.car.data.model.Car
import com.autoservicecrm.car.data.model.Customer
import com.autoservicecrm.shared.ui.composable.isScrollingUp

@Composable
fun CarsLazyListView(
    cars: List<Car>,
    isScrollingUp: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    isScrollingUp.value = listState.isScrollingUp()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = listState
    ) {
        items(cars) {
            it.brand
            it.id
            it.model
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column {
                        ContentInfoView(
                            Modifier,
                            R.drawable.car_icon,
                            it.brand,
                            it.id.toString(),
                            it.model
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                        ContentInfoView(
                            Modifier,
                            R.drawable.user_icon,
                            it.customer.lastname,
                            it.customer.id.toString(),
                            it.customer.name
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.ContentInfoView(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    additionalTitleInfo: String,
    subtitle: String
) {
    Row(modifier) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .align(CenterVertically),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = title, style = MaterialTheme.typography.h6)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = additionalTitleInfo,
            textAlign = TextAlign.End
        )
    }
    Row {
        Spacer(modifier = Modifier.width(56.dp))
        Text(
            modifier = Modifier,
            text = subtitle,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
private fun CarsLazyListPreview() {
    CarsLazyListView(
        listOf(
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
        ),
        isScrollingUp = remember {
            mutableStateOf(true)
        }
    )
}
