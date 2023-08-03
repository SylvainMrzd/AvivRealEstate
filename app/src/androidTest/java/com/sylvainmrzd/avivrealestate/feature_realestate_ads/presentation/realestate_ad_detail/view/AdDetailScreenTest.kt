package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.view

import androidx.activity.compose.setContent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.sylvainmrzd.avivrealestate.di.AppModule
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.MainActivity
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.Screen
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.ui.theme.AvivRealEstateTheme
import com.sylvainmrzd.avivrealestate.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class AdDetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var device: UiDevice

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            AvivRealEstateTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.AdDetailScreen.route
                ) {
                    composable(
                        route = Screen.AdDetailScreen.route + "?adId={adId}",
                        arguments = listOf(
                            navArgument(name = "adId") {
                                type = NavType.IntType
                                defaultValue = 1
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
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun top_bar_is_displayed() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.TOP_BAR_TAG).assertIsDisplayed()
    }

    @Test
    fun back_button_is_displayed() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.BACK_BUTTON_TAG).assertIsDisplayed()
    }

    @Test
    fun ads_list_is_displayed_when_back_button_is_clicked() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.BACK_BUTTON_TAG).performClick()
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun photo_opens_itself_in_fullscreen_when_click_on_it() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.AD_DETAIL_PHOTO_TAG).performClick()
        composeRule.onNodeWithTag(Constants.AD_DETAIL_FULLSCREEN_PHOTO_TAG).assertIsDisplayed()
    }

    @Test
    fun closed_button_is_displayed_in_fullscreen_photo() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.AD_DETAIL_PHOTO_TAG).performClick()
        composeRule.onNodeWithTag(Constants.AD_DETAIL_FULLSCREEN_PHOTO_CLOSE_BUTTON_TAG).assertIsDisplayed()
    }

    @Test
    fun fullscreen_photo_closed_button_displays_back_ad_detail_screen() {
        composeRule.waitUntilTimeout(8_000)
        composeRule.onNodeWithTag(Constants.AD_DETAIL_PHOTO_TAG).performClick()
        composeRule.onNodeWithTag(Constants.AD_DETAIL_FULLSCREEN_PHOTO_CLOSE_BUTTON_TAG).performClick()
        composeRule.onNodeWithTag(Constants.AD_DETAIL_CONTENT_TAG).assertIsDisplayed()
    }


}