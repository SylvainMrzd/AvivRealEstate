package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import com.sylvainmrzd.avivrealestate.R
import java.text.DecimalFormat

/**
 * Format a price Float into a [UiText.StringResource]
 */
fun Float.formatPrice(): UiText.StringResource {
    val priceFormat = DecimalFormat("###,###,###,###,###")
    return UiText.StringResource(
        R.string.price,
        priceFormat.format(this).replace(",", " ")
    )
}

/**
 * Format a area Float into a [UiText.StringResource]
 */
fun Float.formatArea(): UiText.StringResource {
    return UiText.StringResource(
        R.string.square_meter_unit,
        this.toString()
    )
}

/**
 * Format a rooms Int into a [UiText.StringResource]
 */
fun Int.formatRooms(): UiText.StringResource {
    return UiText.StringResource(
        resId = R.string.rooms,
        this.toString()
    )
}

/**
 * Format a bedrooms Int into a [UiText.StringResource]
 */
fun Int.formatBedrooms(): UiText.StringResource {
    return UiText.StringResource(
        resId = R.string.bedrooms,
        this.toString()
    )
}

/**
 * Format a professional into a [UiText.StringResource]
 */
fun String.formatProfessional(): UiText.StringResource {
    return UiText.StringResource(
        R.string.being_sold_by,
        this
    )
}