package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.shimmerEffect

/**
 * Build a placeholder to be used during the image loading in the SubcomposeAsyncImage
 */
@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .shimmerEffect(),
            painter = painterResource(id = R.drawable.outline_home_work),
            contentDescription = stringResource(R.string.image_placeholder_content_description)
        )
    }
}

/**
 * Builds a default Image to be used when the SubcomposeAsyncImage can't load an image
 */
@Composable
fun ErrorImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
                .alpha(0.5f),
            painter = painterResource(id = R.drawable.outline_image_not_supported),
            contentDescription = stringResource(R.string.error_image_content_description)
        )
    }
}