package com.autoservicecrm.master.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.autoservicecrm.master.data.model.Master

@Composable
fun MastersLazyListView(
    masters: List<Master>,
    isScrollingUp: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()

    isScrollingUp.value = true //todo

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = listState,
        columns = GridCells.Fixed(2)
    ) {
        items(masters) {
            this@LazyVerticalGrid.ContentInfoView(master = it)
        }
    }
}

@Composable
fun LazyGridScope.ContentInfoView(
    modifier: Modifier = Modifier,
    master: Master
) {
    Card(
        modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://xsgames.co/randomusers/avatar.php?g=male",
                    contentDescription = null
                )
                Text(text = master.name, style = MaterialTheme.typography.h6)
                Text(text = master.surname, style = MaterialTheme.typography.h6)
                Text(text = master.phone, style = MaterialTheme.typography.subtitle1)
                Text(
                    text = "ID: ${master.id}",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Preview
@Composable
private fun MastersLazyListPreview() {
    MastersLazyListView(
        listOf(
            Master(
                1,
                "Ivan",
                "Puploenko",
                "+380931350614"
            ),
            Master(
                1,
                "Ivan",
                "Puploenko",
                "+380931350614"
            ),
            Master(
                1,
                "Ivan",
                "Puploenko",
                "+380931350614"
            ),
            Master(
                1,
                "Ivan",
                "Puploenko",
                "+380931350614"
            ),
            Master(
                1,
                "Ivan",
                "Puploenko",
                "+380931350614"
            ),
        ),
        isScrollingUp = remember {
            mutableStateOf(true)
        }
    )
}
