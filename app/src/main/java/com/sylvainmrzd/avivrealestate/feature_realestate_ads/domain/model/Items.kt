package com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model

/**
 * Represents the items, with the given list of [items] and the [totalCount]
 */
data class Items(
    val items: List<Ad>,
    val totalCount: Int
)
