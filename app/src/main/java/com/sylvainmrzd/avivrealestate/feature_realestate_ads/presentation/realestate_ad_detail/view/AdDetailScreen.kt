package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.AdDetail
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.DataLoadFailedMessage
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.ShimmerAdDetail
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.viewmodel.AdDetailViewModel

@Composable
fun AdDetailScreen(
    navController: NavController,
    viewModel: AdDetailViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            viewModel.isAdDetailFetchingInProgress -> {
                ShimmerAdDetail()
            }
            !viewModel.errorMessage.isNullOrEmpty() -> {
                DataLoadFailedMessage() { viewModel.fetchAdDetail(viewModel.adId) }
            }
            else -> AdDetail(
                navController = navController,
                infoToDisplay = viewModel.adInfo
            )
        }
    }
}