package com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model

data class Ad(
    val bedrooms: Int,
    val city: String,
    val id: Int,
    val area: Float,
    val url: String,
    val price: Float,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int
)
