package com.sylvainmrzd.avivrealestate.feature_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.AvivApi
import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.MockInterceptor
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.feature_ads.domain.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.others.Constants
import com.sylvainmrzd.avivrealestate.others.Resource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository used for tests to simulate the api behavior
 */
class FakeAvivRealEstateRepositoryImpl : AvivRealEstateRepository {

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
                    handleResponse(response)
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

    override suspend fun loadAdDetail(id: Int): Resource<Ad> {
        return when {
            shouldMockRequest -> {
                try {
                    val response = getMockAvivApi().getAdDetail(id)
                    handleResponse(response)
                } catch (e: Exception) {
                    Resource.error(Constants.CANNOT_REACH_SERVER_ERROR, null)
                }
            }
            else -> {
                if (shouldReturnNetworkError) {
                    Resource.error(Constants.CANNOT_REACH_SERVER_ERROR, null)
                } else {
                    Resource.success(null)
                }
            }
        }
    }

    private fun getMockAvivApi(): AvivApi {
        val client = OkHttpClient.Builder().addInterceptor(MockInterceptor()).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(AvivApi::class.java)
    }
}