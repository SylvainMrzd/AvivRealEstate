package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines requests available for this API
 */
interface AvivApi {

    @GET("listings.json")
    suspend fun getAdsItems(): Response<Items>

    @GET("listings/{adId}.json")
    suspend fun getAdDetail(@Path("adId") id: Int): Response<Ad>
}