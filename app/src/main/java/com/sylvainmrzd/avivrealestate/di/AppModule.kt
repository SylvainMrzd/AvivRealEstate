package com.sylvainmrzd.avivrealestate.di

import com.sylvainmrzd.avivrealestate.feature_ads.data.data_source.AvivApi
import com.sylvainmrzd.avivrealestate.feature_ads.domain.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_ads.data.repository.AvivRealEstateRepositoryImpl
import com.sylvainmrzd.avivrealestate.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAvivApi(): AvivApi {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AvivApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultAvivRepository(api: AvivApi) = AvivRealEstateRepositoryImpl(api) as AvivRealEstateRepository
}