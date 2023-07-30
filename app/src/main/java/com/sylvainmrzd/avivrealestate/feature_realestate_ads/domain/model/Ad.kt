package com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model

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
)
