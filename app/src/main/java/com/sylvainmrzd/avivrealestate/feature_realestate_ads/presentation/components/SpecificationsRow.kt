package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

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
import androidx.compose.ui.unit.dp
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.DisplayedScreen

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
                modifier = key.getViewModifier(DisplayedScreen.ADS_LIST),
                text = spec,
                style = key.getTextStyle(DisplayedScreen.ADS_LIST),
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