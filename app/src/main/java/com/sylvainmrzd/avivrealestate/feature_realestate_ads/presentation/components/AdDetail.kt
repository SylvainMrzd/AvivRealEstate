package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.DisplayedScreen
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.UiText
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.Screen
import com.sylvainmrzd.avivrealestate.others.Constants

/**
 * Builds the Item that displays ad info from [infoToDisplay]
 * Ad Item is dynamically build to be used in a list
 */
@Composable
fun AdDetail(
    modifier: Modifier,
    navController: NavController,
    infoToDisplay: Map<AdElements, UiText>?
) {

    val orderedInfoToDisplay = listOf(
        AdElements.IMAGE_URL,
        AdElements.PROPERTY_TYPE,
        AdElements.SPECIFICATIONS,
        AdElements.CITY,
        AdElements.PRICE,
        AdElements.PROFESSIONAL
    )

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
                .testTag(Constants.AD_DETAIL_CONTENT_TAG)
        ) {
            infoToDisplay?.let { infoToDisplay ->
                for (key in orderedInfoToDisplay) {
                    when(key) {
                        AdElements.IMAGE_URL -> {
                            val url = infoToDisplay[key]?.asString()

                            SubcomposeAsyncImage(
                                modifier = key
                                    .getViewModifier(DisplayedScreen.AD_DETAIL)
                                    .clickable(enabled = !url.isNullOrEmpty()) {
                                        navController.navigate(
                                            Screen.AdOpenedPhoto.route + "?photoUrl=${url}"
                                        )
                                    }
                                    .testTag(Constants.AD_DETAIL_PHOTO_TAG),
                                contentScale = ContentScale.Crop,
                                model = infoToDisplay[key]?.asString(),
                                loading = { ImagePlaceholder() },
                                error = { ErrorImage() },
                                contentDescription = stringResource(R.string.ad_photo_content_description)
                            )
                        }
                        AdElements.PRICE -> {
                            Row(
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    modifier = key.getViewModifier(DisplayedScreen.AD_DETAIL),
                                    style = key.getTextStyle(DisplayedScreen.AD_DETAIL),
                                    fontWeight = key.getTextFontWeight(),
                                    text = infoToDisplay[key]?.asString() ?: ""
                                )
                                val squareMeterPriceKey = AdElements.SQUARE_METER_PRICE
                                infoToDisplay[squareMeterPriceKey]?.let {
                                    Text(
                                        modifier = squareMeterPriceKey.getViewModifier(
                                            DisplayedScreen.AD_DETAIL),
                                        style = squareMeterPriceKey.getTextStyle(DisplayedScreen.AD_DETAIL),
                                        fontWeight = squareMeterPriceKey.getTextFontWeight(),
                                        text = infoToDisplay[squareMeterPriceKey]?.asString() ?: ""
                                    )
                                }
                            }
                        }
                        AdElements.PROPERTY_TYPE,
                        AdElements.CITY,
                        AdElements.PROFESSIONAL -> {
                            Text(
                                modifier = key.getViewModifier(DisplayedScreen.AD_DETAIL),
                                style = key.getTextStyle(DisplayedScreen.AD_DETAIL),
                                fontWeight = key.getTextFontWeight(),
                                text = infoToDisplay[key]?.asString() ?: ""
                            )
                        }
                        AdElements.SPECIFICATIONS -> {
                            SpecificationsRow(
                                key = key,
                                specifications = infoToDisplay[key]?.asString() ?: ""
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}