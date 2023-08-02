package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source.MockAvivApi
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source.MockInterceptor
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.others.Resource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository used for tests to simulate the api behavior
 */
class FakeAvivRealEstateRepository : AvivRealEstateRepository {

    private var shouldReturnNetworkError = false
    private var shouldMockRequest = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }
    fun setShouldMockRequest(value: Boolean) {
        shouldMockRequest = value
    }

    override suspend fun loadAdsList(): Resource<Items> {
        return when {
            shouldMockRequest -> {
                try {
                    val response = getMockAvivApi().getAdsItems()
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
            else -> {
                if (shouldReturnNetworkError) {
                    Resource.error(Constants.CANNOT_REACH_SERVER_ERROR, null)
                } else {
                    Resource.success(Items(listOf(), 0))
                }
            }
        }
    }

    private fun getMockAvivApi(): MockAvivApi {
        val client = OkHttpClient.Builder().addInterceptor(MockInterceptor()).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(MockAvivApi::class.java)
    }
}