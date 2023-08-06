package com.sylvainmrzd.avivrealestate.feature_ads.presentation.ads_list

import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.UiText

data class AdsState(
    val ads: List<Map<AdElements, UiText>?> = emptyList()
)
