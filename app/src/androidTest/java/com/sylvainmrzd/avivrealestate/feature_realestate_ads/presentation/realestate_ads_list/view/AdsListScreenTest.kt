package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import com.sylvainmrzd.avivrealestate.di.AppModule
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.MainActivity
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class AdsListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun scrollAdsListUpAndDown() {
        composeRule.waitUntilTimeout(10_000)
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeUp() }
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeDown() }
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeUp() }
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeDown() }
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeUp() }
        composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performTouchInput { swipeDown() }
    }

    @Test
    fun list_contains_all_elements() {
        composeRule.waitUntilTimeout(10_000)

        for (i in 0..7) {
            composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performScrollToIndex(i)
            composeRule.onNodeWithTag(Constants.ADS_ITEM_TAG.plus(i)).assertExists()
        }
    }

    @Test
    fun all_elements_are_displayed() {
        composeRule.waitUntilTimeout(10_000)

        for (i in 0..7) {
            composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performScrollToIndex(i)
            composeRule.onNodeWithTag(Constants.ADS_ITEM_TAG.plus(i)).assertIsDisplayed()
        }
    }

    @Test
    fun click_on_list_element() {
        composeRule.waitUntilTimeout(10_000)

        for (i in 0..7) {
            composeRule.onNodeWithTag(Constants.ADS_LIST_TAG).performScrollToIndex(i)
            composeRule.onNodeWithTag(Constants.ADS_ITEM_TAG.plus(i)).performClick()
        }
    }
}