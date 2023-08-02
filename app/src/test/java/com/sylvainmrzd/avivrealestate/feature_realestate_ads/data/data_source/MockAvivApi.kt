package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import retrofit2.Response
import retrofit2.http.GET

/**
 * Defines requests available for this API
 */
interface MockAvivApi {

    @GET("listings.json")
    suspend fun getAdsItems(): Response<Items>
}