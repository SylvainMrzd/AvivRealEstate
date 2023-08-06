package com.sylvainmrzd.avivrealestate.feature_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.others.Resource
import retrofit2.Response

interface AvivRealEstateRepository {

    suspend fun loadAdsList(): Resource<Items>
    suspend fun loadAdDetail(id: Int): Resource<Ad>

    fun <T> handleResponse(response: Response<T>) : Resource<T> {
        return if(response.isSuccessful) {
            response.body()?.let {
                return@let Resource.success(it)
            } ?: Resource.error(Constants.UNKNOWN_ERROR, null)
        } else {
            Resource.error(Constants.UNKNOWN_ERROR, null)
        }
    }
}