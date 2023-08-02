package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Resource

interface AvivRealEstateRepository {

    suspend fun loadAdsList(): Resource<Items>
}