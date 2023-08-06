package com.sylvainmrzd.avivrealestate.feature_ads.presentation.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.DisplayedScreen

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
        val mediumPadding = dimensionResource(id = R.dimen.medium_padding)
        val dividerWidth = dimensionResource(id = R.dimen.divider_width)

        val specificationsList = specifications.split(";")
        for (spec in specificationsList) {
            Text(
                modifier = key.getViewModifier(DisplayedScreen.ADS_LIST),
                text = spec,
                style = key.getTextStyle(DisplayedScreen.ADS_LIST),
                fontWeight = key.getTextFontWeight()
            )
            if (specificationsList.last() != spec) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = mediumPadding, end = mediumPadding, bottom = mediumPadding)
                        .fillMaxHeight()
                        .width(dividerWidth)
                )
            }
        }
    }
}