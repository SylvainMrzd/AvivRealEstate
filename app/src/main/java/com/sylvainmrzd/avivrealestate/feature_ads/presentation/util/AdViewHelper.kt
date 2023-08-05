package com.sylvainmrzd.avivrealestate.feature_ads.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
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
enum class DisplayedScreen {
    ADS_LIST,
    AD_DETAIL,
    AD_OPENED_PHOTO
}

enum class PropertyTypes(val value: String) {
    HOUSE_VILLA("Maison - Villa"),
    APARTMENT("Appartement"),
    BUILDING("Immeuble"),
    STORE("Commerce"),
    LAND("Terrain"),
    GARAGE_LOCKUP("Garage / Box")
}

enum class AdElements : AdElementsSettings {

    IMAGE_URL,
    PRICE,
    SQUARE_METER_PRICE,
    PROPERTY_TYPE,
    SPECIFICATIONS,
    CITY,
    PROFESSIONAL,
    ID;

    @Composable
    override fun getTextStyle(displayedScreen: DisplayedScreen): TextStyle {
        return when(displayedScreen) {
            DisplayedScreen.ADS_LIST -> {
                when(this) {
                    PRICE -> MaterialTheme.typography.headlineLarge
                    else -> MaterialTheme.typography.bodyMedium
                }
            }
            DisplayedScreen.AD_DETAIL -> {
                when(this) {
                    PRICE, PROPERTY_TYPE -> MaterialTheme.typography.headlineLarge
                    SQUARE_METER_PRICE -> MaterialTheme.typography.bodySmall
                    else -> MaterialTheme.typography.bodyMedium
                }
            }
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

    override fun getViewModifier(displayedScreen: DisplayedScreen): Modifier {
        return when(displayedScreen) {
            DisplayedScreen.ADS_LIST -> {
                when(this) {
                    IMAGE_URL -> Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                    PRICE, SPECIFICATIONS, PROFESSIONAL -> Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    else -> Modifier.padding(start = 8.dp, end = 8.dp)
                }
            }
            DisplayedScreen.AD_DETAIL -> {
                when(this) {
                    PROPERTY_TYPE -> Modifier.padding(8.dp)
                    SPECIFICATIONS -> Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    IMAGE_URL -> Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                    PRICE -> Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
                    PROFESSIONAL -> Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
                    else -> Modifier.padding(start = 8.dp, end = 8.dp)
                }
            }
            DisplayedScreen.AD_OPENED_PHOTO -> {
                when(this) {
                    IMAGE_URL -> Modifier.fillMaxSize()
                    else -> Modifier
                }
            }
        }
    }
}

private interface AdElementsSettings {

    @Composable
    fun getTextStyle(displayedScreen: DisplayedScreen) : TextStyle
    @Composable
    fun getTextFontWeight() : FontWeight
    fun getViewModifier(displayedScreen: DisplayedScreen): Modifier
}