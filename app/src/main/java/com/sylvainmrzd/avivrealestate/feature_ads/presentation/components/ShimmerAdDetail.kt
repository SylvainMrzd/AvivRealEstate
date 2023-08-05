package com.sylvainmrzd.avivrealestate.feature_ads.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.shimmerEffect

/**
 * Builds a shimmer item used in the ads list while data fetching from the server is in progress
 */
@Composable
fun ShimmerAdDetail(
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(30.dp)
                .padding(start = 8.dp, end = 8.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(14.dp)
                .padding(start = 8.dp, end = 8.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(14.dp)
                .padding(start = 8.dp, end = 8.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(30.dp)
                .padding(start = 8.dp, end = 8.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(14.dp)
                .padding(start = 8.dp, end = 8.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun ShimmerAdDetailPreview() {
    ShimmerAdDetail(Modifier)
}