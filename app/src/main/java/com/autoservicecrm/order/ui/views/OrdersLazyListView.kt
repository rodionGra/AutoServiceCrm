@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)

package com.autoservicecrm.order.ui.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.order.data.model.Order
import com.autoservicecrm.order.data.model.TuningBox
import com.autoservicecrm.shared.ui.composable.isScrollingUp
import com.autoservicecrm.shared.ui.theme.White
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun OrdersLazyListView(
    orders: List<Order>,
    isScrollingUp: MutableState<Boolean>,
    removeItem: (Order) -> Unit,
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
        items(orders, key = Order::id) { order ->
            val currentItem by rememberUpdatedState(order)
            val dismissState = rememberDismissState(
                confirmStateChange = { dismissValue ->
                    when (dismissValue) {
                        DismissValue.DismissedToStart -> {
                            removeItem(currentItem)
                            true
                        }

                        else -> false
                    }
                }
            )
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .animateItemPlacement(),
                background = {
                    dismissState.SwipeBackground()
                },
                dismissContent = {
                    ContentInfoView(order = order)
                }
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun DismissState.SwipeBackground() {
    val direction = this.dismissDirection ?: return

    val color by animateColorAsState(
        when (this.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> Color.Red
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Done
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (this.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            modifier = Modifier.scale(scale)
        )
    }
}

@Composable
fun LazyItemScope.ContentInfoView(
    modifier: Modifier = Modifier,
    order: Order
) {
    Card(
        modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = White,
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                Text(text = order.description, style = MaterialTheme.typography.h6)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    val startTime = LocalDateTime.parse(order.startDate.substringBefore('.'))
                    val endTime = LocalDateTime.parse(order.endDate.substringBefore('.'))

                    Text(
                        text =
                        "Create: ${startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))} " +
                                "\nEnd: ${endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Text(
                    text = stringResource(
                        id = R.string.price,
                        formatArgs = arrayOf(order.price.toString())
                    ), style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = stringResource(
                        id = R.string.tuning_box_id,
                        formatArgs = arrayOf(order.tuningBox.boxNumber.toString())
                    ),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Preview
@Composable
private fun OrdersLazyListViewPreview() {
    OrdersLazyListView(
        orders = listOf(
            Order(
                startDate = "2016-12-08T10:20:30",
                endDate = "2016-12-08T10:20:30",
                description = "Long Desciption Puploenko",
                price = 1000.00,
                tuningBox = TuningBox(3),
                isDone = true,
                id = 1
            )
        ),
        isScrollingUp = remember {
            mutableStateOf(true)
        },
        removeItem = {}
    )
}
