package com.sylvainmrzd.avivrealestate.feature_ads.presentation.ad_detail.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.components.AdDetail
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.components.DataLoadFailedMessage
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.components.ShimmerAdDetail
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.components.TopBar
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.ad_detail.viewmodel.AdDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdDetailScreen(
    navController: NavController,
    viewModel: AdDetailViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                viewModel = viewModel
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPadding ->
        when {
            viewModel.isAdDetailFetchingInProgress -> {
                ShimmerAdDetail(modifier = Modifier.padding(innerPadding))
            }
            !viewModel.errorMessage.isNullOrEmpty() -> {
                DataLoadFailedMessage(modifier = Modifier.padding(innerPadding)) {
                    viewModel.fetchAdDetail(viewModel.adId)
                }
            }
            else -> AdDetail(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                infoToDisplay = viewModel.adInfo
            )
        }
    }
}