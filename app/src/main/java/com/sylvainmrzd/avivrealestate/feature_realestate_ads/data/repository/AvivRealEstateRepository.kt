package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Resource

interface AvivRealEstateRepository {

    suspend fun loadAdsList(): Resource<Items>
    suspend fun loadAdDetail(id: Int): Resource<Ad>
}