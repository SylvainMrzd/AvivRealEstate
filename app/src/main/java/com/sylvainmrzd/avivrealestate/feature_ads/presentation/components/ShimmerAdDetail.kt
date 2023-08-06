package com.sylvainmrzd.avivrealestate.feature_ads.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.shimmerEffect

/**
 * Builds a shimmer item used in the ads list while data fetching from the server is in progress
 */
@Composable
fun ShimmerAdDetail(
    modifier: Modifier
) {

    val imageHeight = dimensionResource(id = R.dimen.image_height)
    val mediumPadding = dimensionResource(id = R.dimen.medium_padding)
    val bigPadding = dimensionResource(id = R.dimen.big_padding)
    val bigTextShimmerHeight = dimensionResource(id = R.dimen.big_text_shimmer_height)
    val mediumTextShimmerHeight = dimensionResource(id = R.dimen.medium_text_shimmer_height)

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(bigTextShimmerHeight)
                .padding(start = mediumPadding, end = mediumPadding)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(mediumTextShimmerHeight)
                .padding(start = mediumPadding, end = mediumPadding)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(mediumTextShimmerHeight)
                .padding(start = mediumPadding, end = mediumPadding)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(bigPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(bigTextShimmerHeight)
                .padding(start = mediumPadding, end = mediumPadding)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(bigPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(mediumTextShimmerHeight)
                .padding(start = mediumPadding, end = mediumPadding)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(mediumPadding))
    }
}

@Preview
@Composable
fun ShimmerAdDetailPreview() {
    ShimmerAdDetail(Modifier)
}