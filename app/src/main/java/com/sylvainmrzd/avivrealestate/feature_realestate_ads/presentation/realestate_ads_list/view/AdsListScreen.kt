package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.AdItem
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.ShimmerAdItem
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.viewmodel.AdsListViewModel
import com.sylvainmrzd.avivrealestate.ui.theme.AvivRealEstateTheme

/**
 * Build the screen that will displays all the ads in a list
 */
@Composable
fun AdsListScreen(
    /*navController: NavController,*/
    viewModel: AdsListViewModel = hiltViewModel()
) {

    viewModel.getAdsList()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        /**
         * While the fetch of the ads is in progress, displays 5 shimmer items
         * Then displays the AdItems list with the fetched data
         */
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            when {
                viewModel.isAdsFetchingInProgress -> {
                    items(5) {
                        ShimmerAdItem()
                    }
                }
                else -> {
                    itemsIndexed(items = viewModel.adsInfoList) { index, item ->
                        AdItem(infoToDisplay = item, index) {
                            // TODO Navigation to open ad details
                        }
                    }
                }
            }
        }
        when {
            viewModel.errorMessage.isNotEmpty() -> {
                AdsLoadFailedMessage() { viewModel.getAdsList() }
            }
        }
    }
}

/**
 * Message and retry button displayed when an error occurred while loading the ads list from the server
 */
@Composable
fun AdsLoadFailedMessage(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.outline_error),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(R.string.ad_loading_error_image_content_description)
        )
        Text(
            text = stringResource(R.string.fetching_ads_error),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { onClick() }
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdsListScreenPreview() {
    AvivRealEstateTheme {
        AdsListScreen()
    }
}