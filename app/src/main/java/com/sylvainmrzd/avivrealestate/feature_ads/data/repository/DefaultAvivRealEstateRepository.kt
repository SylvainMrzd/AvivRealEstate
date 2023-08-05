package com.sylvainmrzd.avivrealestate.feature_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.AvivApi
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.others.Resource
import javax.inject.Inject

/**
 * Repository to get data from the [avivApi]
 */
class DefaultAvivRealEstateRepository @Inject constructor(
    private val avivApi: AvivApi
) : AvivRealEstateRepository {

    override suspend fun loadAdsList(): Resource<Items> {
        return try {
            val response = avivApi.getAdsItems()
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(Constants.UNKNOWN_ERROR, null)
            } else {
                Resource.error(Constants.UNKNOWN_ERROR, null)
            }
        } catch (e: Exception) {
            Resource.error(Constants.CANNOT_REACH_SERVER_ERROR, null)
        }
    }

    override suspend fun loadAdDetail(id: Int): Resource<Ad> {
        return try {
            val response = avivApi.getAdDetail(id)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(Constants.UNKNOWN_ERROR, null)
            } else {
                Resource.error(Constants.UNKNOWN_ERROR, null)
            }
        } catch (e: Exception) {
            Resource.error(Constants.CANNOT_REACH_SERVER_ERROR, null)
        }
    }
}