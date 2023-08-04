package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sylvainmrzd.avivrealestate.R

/**
 * Message and retry button displayed when an error occurred while loading data from the server
 */
@Composable
fun DataLoadFailedMessage(
    modifier: Modifier,
    text: String? = stringResource(R.string.fetching_data_error),
    hasRetryAction: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.outline_error),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(R.string.data_loading_error_image_content_description)
        )
        Text(
            text = text ?: stringResource(R.string.fetching_data_error),
            textAlign = TextAlign.Center
        )
        if (hasRetryAction) {
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = { onClick() }
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}