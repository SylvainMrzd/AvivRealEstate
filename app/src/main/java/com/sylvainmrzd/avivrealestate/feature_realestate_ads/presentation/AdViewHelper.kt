package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Enum that lists elements contained in an ad
 * and implements [AdElementsSettings] interface to dynamically set text style, font and modifier
 * to those elements
 */
enum class AdElements : AdElementsSettings {

    IMAGE_URL,
    PRICE,
    PROPERTY_TYPE,
    SPECIFICATIONS,
    CITY,
    PROFESSIONAL;

    @Composable
    override fun getTextStyle(): TextStyle {
        return when(this) {
            PRICE -> MaterialTheme.typography.headlineLarge
            else -> MaterialTheme.typography.bodyMedium
        }
    }

    @Composable
    override fun getTextFontWeight(): FontWeight {
        return when(this) {
            PRICE, PROPERTY_TYPE, SPECIFICATIONS -> FontWeight.Bold
            else -> FontWeight.Normal
        }
    }


    override fun getModifier(): Modifier {
        return when(this) {
            IMAGE_URL -> Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
            PRICE, SPECIFICATIONS, PROFESSIONAL -> Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            else -> Modifier.padding(start = 8.dp, end = 8.dp)
        }
    }
}

private interface AdElementsSettings {

    @Composable
    fun getTextStyle() : TextStyle
    @Composable
    fun getTextFontWeight() : FontWeight
    fun getModifier(): Modifier
}