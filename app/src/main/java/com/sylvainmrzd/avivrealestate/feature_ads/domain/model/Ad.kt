package com.sylvainmrzd.avivrealestate.feature_ads.domain.model

import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.UiText
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatArea
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatBedrooms
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatPrice
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatProfessional
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatRooms
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.formatSquareMeterPrice

/**
 * Represents an ad, with the given number of [bedrooms], [city], [id], surface [area], image [url],
 * [price], [professional], [propertyType], [offerType] and number of [rooms],
 */
data class Ad(
    val bedrooms: Int?,
    val city: String?,
    val id: Int,
    val area: Float?,
    val url: String,
    val price: Float,
    val professional: String?,
    val propertyType: String?,
    val offerType: Int?,
    val rooms: Int?
) {

    val infoToDisplay
        get() = getInfoToDisplayFrom(this)

    /**
     * Create Map<AdElements, UiText> from [ad] to dynamically get info to display in the UI
     */
    private fun getInfoToDisplayFrom(ad: Ad): Map<AdElements, UiText> {

        val infoToDisplay = mutableMapOf<AdElements, UiText>()

        infoToDisplay[AdElements.ID] = UiText.IntValue(ad.id)
        infoToDisplay[AdElements.IMAGE_URL] = UiText.StringValue(ad.url)

        infoToDisplay[AdElements.PRICE] = ad.price.formatPrice()
        ad.propertyType?.let {
            infoToDisplay[AdElements.PROPERTY_TYPE] = UiText.StringValue(it)
        }

        val specifications = mutableListOf<UiText>()
        ad.area?.let {
            infoToDisplay[AdElements.SQUARE_METER_PRICE] =
                (ad.price / ad.area).toInt().formatSquareMeterPrice()
            specifications.add(it.formatArea())
        }
        ad.rooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.StringValue(";"))
            }
            specifications.add(it.formatRooms())
        }
        ad.bedrooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.StringValue(";"))
            }
            specifications.add(it.formatBedrooms())
        }
        if(specifications.isNotEmpty()) {
            infoToDisplay[AdElements.SPECIFICATIONS] = UiText.CombinedStrings(specifications)
        }

        ad.city?.let {
            infoToDisplay[AdElements.CITY] = UiText.StringValue(it)
        }

        ad.professional?.let {
            infoToDisplay[AdElements.PROFESSIONAL] = it.formatProfessional()
        }

        return infoToDisplay
    }
}
