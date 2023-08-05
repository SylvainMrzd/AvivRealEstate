package com.sylvainmrzd.avivrealestate.di

import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.AvivApi
import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.MockInterceptor
import com.sylvainmrzd.avivrealestate.feature_ads.data.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_ads.data.repository.DefaultAvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideAvivApi(): AvivApi {
        val client = OkHttpClient.Builder().addInterceptor(MockInterceptor()).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(AvivApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultAvivRepository(api: AvivApi) = DefaultAvivRealEstateRepository(api) as AvivRealEstateRepository
}