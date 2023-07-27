package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("listings.json")
    suspend fun getAdsItems(): Items

    companion object {
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder().addInterceptor(logging).build()

        private var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://gsl-apps-technical-test.dignp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}