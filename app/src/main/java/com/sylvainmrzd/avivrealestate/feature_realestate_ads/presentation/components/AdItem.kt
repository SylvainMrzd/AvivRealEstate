package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.UiText
import com.sylvainmrzd.avivrealestate.others.Constants

/**
 * Builds the Item that displays ad info from [infoToDisplay]
 * Ad Item is dynamically build to be used in a list
 */
@Composable
fun AdItem(
    infoToDisplay: Map<AdElements, UiText>,
    index: Int,
    onClick: (Int) -> Unit
) {

    ElevatedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .testTag(Constants.ADS_ITEM_TAG.plus(index)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for (key in infoToDisplay.keys) {
                    when(key) {
                        AdElements.IMAGE_URL -> {
                            SubcomposeAsyncImage(
                                modifier = key.getModifier(),
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
                                modifier = key.getModifier(),
                                style = key.getTextStyle(),
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
                    }
                }
            }
        }
    }
}

/**
 * Builds a row to display [specifications] info, such as surface area,number of rooms
 * and number of bedrooms
 */
@Composable
fun SpecificationsRow(key: AdElements, specifications: String) {
    Row(
        Modifier
            .height(IntrinsicSize.Min) //intrinsic measurements
            .fillMaxWidth()
    ) {
        val specificationsList = specifications.split(";")
        for (spec in specificationsList) {
            Text(
                modifier = key.getModifier(),
                text = spec,
                style = key.getTextStyle(),
                fontWeight = key.getTextFontWeight()
            )
            if (specificationsList.last() != spec) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp, bottom = 8.dp)
                        .fillMaxHeight()
                        .width(1.dp)
                )
            }
        }
    }
}

/**
 * Build a placeholder to be used during the image loading in the SubcomposeAsyncImage
 */
@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .shimmerEffect(),
            painter = painterResource(id = R.drawable.outline_home_work),
            contentDescription = stringResource(R.string.image_placeholder_content_description)
        )
    }
}

/**
 * Builds a default Image to be used when the SubcomposeAsyncImage can't load an image
 */
@Composable
fun ErrorImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
                .alpha(0.5f),
            painter = painterResource(id = R.drawable.outline_image_not_supported),
            contentDescription = stringResource(R.string.error_image_content_description)
        )
    }
}