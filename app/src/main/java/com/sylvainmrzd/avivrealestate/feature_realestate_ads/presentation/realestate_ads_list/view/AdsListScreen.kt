package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.AdItem
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.DataLoadFailedMessage
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.ShimmerAdItem
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components.TopBar
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.viewmodel.AdsListViewModel
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.Screen
import com.sylvainmrzd.avivrealestate.others.Constants

/**
 * Build the screen that will displays all the ads in a list
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdsListScreen(
    navController: NavController,
    viewModel: AdsListViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                hasBackButton = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        /**
         * While the fetch of the ads is in progress, displays 5 shimmer items
         * Then displays the AdItems list with the fetched data
         */
        /**
         * While the fetch of the ads is in progress, displays 5 shimmer items
         * Then displays the AdItems list with the fetched data
         */
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag(Constants.ADS_LIST_TAG)
        ) {
            when {
                viewModel.isAdsFetchingInProgress -> {
                    items(5) {
                        ShimmerAdItem()
                    }
                }
                else -> {
                    itemsIndexed(items = viewModel.state.value.ads) { index, item ->
                        AdItem(
                            infoToDisplay = item,
                            index
                        ) {
                            navController.navigate(
                                Screen.AdDetailScreen.route +
                                        "?adId=${item?.get(AdElements.ID)?.asInt()}"
                            )
                        }
                    }
                }
            }
        }
        when {
            !viewModel.errorMessage.isNullOrEmpty() -> {
                DataLoadFailedMessage(modifier = Modifier.padding(innerPadding)) {
                    viewModel.fetchAdsList()
                }
            }
        }
    }
}