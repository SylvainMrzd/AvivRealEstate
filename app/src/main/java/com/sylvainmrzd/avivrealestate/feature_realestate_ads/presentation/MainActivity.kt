package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.view.AdDetailScreen
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.view.AdOpenedPhotoScreen
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.view.AdsListScreen
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.Screen
import com.sylvainmrzd.avivrealestate.ui.theme.AvivRealEstateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvivRealEstateTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AdsListScreen.route
                    ) {
                        composable(route = Screen.AdsListScreen.route) {
                            AdsListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AdDetailScreen.route + "?adId={adId}",
                            arguments = listOf(
                                navArgument(name = "adId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AdDetailScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AdOpenedPhoto.route + "?photoUrl={photoUrl}",
                            arguments = listOf(
                                navArgument(name = "photoUrl") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            val url = it.arguments?.getString("photoUrl") ?: ""
                            AdOpenedPhotoScreen(
                                photoUrl = url,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}