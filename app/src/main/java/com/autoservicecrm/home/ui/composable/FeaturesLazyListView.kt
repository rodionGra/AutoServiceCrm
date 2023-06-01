@file:OptIn(ExperimentalMaterialApi::class)

package com.autoservicecrm.home.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autoservicecrm.R
import com.autoservicecrm.home.ui.models.FeatureUiModel
import com.autoservicecrm.navigation.Screen
import com.autoservicecrm.shared.ui.theme.BlueLight

@Composable
fun FeaturesLazyListView(
    modifier: Modifier = Modifier,
    features: List<FeatureUiModel>,
    onClick: (Screen) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(features) {
            Card(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(CornerSize(10.dp)),
                backgroundColor = BlueLight,
                onClick = {
                    onClick(it.navigateTo)
                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(id = it.title),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun FeaturesLazyListPreview() {
    FeaturesLazyListView(
        features = listOf(FeatureUiModel(R.string.car_management_title, Screen.HomeScreen))
    ) {

    }
}
