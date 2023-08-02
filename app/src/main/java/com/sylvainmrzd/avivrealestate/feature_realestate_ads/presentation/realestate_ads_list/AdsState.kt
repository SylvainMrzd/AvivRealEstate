package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list

import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.UiText

data class AdsState(
    val ads: List<Map<AdElements, UiText>?> = emptyList()
)
