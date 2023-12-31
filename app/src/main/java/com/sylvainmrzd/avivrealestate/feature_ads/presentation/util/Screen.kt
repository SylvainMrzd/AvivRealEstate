package com.sylvainmrzd.avivrealestate.feature_ads.presentation.util

/**
 * Lists each screen and their route for navigation
 */
sealed class Screen(val route: String) {
    object AdsListScreen: Screen("ads_list_screen")
    object AdDetailScreen: Screen("ad_detail_screen")
    object AdOpenedPhoto: Screen("ad_opened_photo")
}