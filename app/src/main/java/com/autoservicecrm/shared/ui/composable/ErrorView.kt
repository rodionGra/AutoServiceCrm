package com.autoservicecrm.shared.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.autoservicecrm.R

@Composable
fun ErrorView(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.error_image),
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun ErrorViewPreview() {
    ErrorView()
}
