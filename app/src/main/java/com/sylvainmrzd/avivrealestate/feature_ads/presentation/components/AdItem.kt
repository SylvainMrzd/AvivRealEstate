package com.sylvainmrzd.avivrealestate.feature_ads.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.DisplayedScreen
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.UiText
import com.sylvainmrzd.avivrealestate.others.Constants

/**
 * Builds the Item that displays ad info from [infoToDisplay]
 * Ad Item is dynamically build to be used in a list
 */
@Composable
fun AdItem(
    infoToDisplay: Map<AdElements, UiText>?,
    index: Int,
    onClick: (Int) -> Unit
) {

    val roundedCornerShapeSize = dimensionResource(id = R.dimen.rounded_corner_shape_size)
    val smallPadding = dimensionResource(id = R.dimen.small_padding)

    val orderedInfoToDisplay = listOf(
        AdElements.IMAGE_URL,
        AdElements.PRICE,
        AdElements.PROPERTY_TYPE,
        AdElements.SPECIFICATIONS,
        AdElements.CITY,
        AdElements.PROFESSIONAL
    )

    ElevatedCard(
        modifier = Modifier
            .padding(smallPadding)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .testTag(Constants.ADS_ITEM_TAG.plus(index)),
        shape = RoundedCornerShape(roundedCornerShapeSize)
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                infoToDisplay?.let { infoToDisplay ->
                    for (key in orderedInfoToDisplay) {
                        when(key) {
                            AdElements.IMAGE_URL -> {
                                SubcomposeAsyncImage(
                                    modifier = key.getViewModifier(DisplayedScreen.ADS_LIST),
                                    contentScale = ContentScale.Crop,
                                    model = infoToDisplay[key]?.asString(),
                                    loading = { ImagePlaceholder() },
                                    error = { ErrorImage() },
                                    contentDescription = stringResource(R.string.ad_photo_content_description)
                                )
                            }
                            AdElements.PRICE,
                            AdElements.PROPERTY_TYPE,
                            AdElements.CITY,
                            AdElements.PROFESSIONAL -> {
                                Text(
                                    modifier = key.getViewModifier(DisplayedScreen.ADS_LIST),
                                    style = key.getTextStyle(DisplayedScreen.ADS_LIST),
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
}