package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source.ApiService
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.UiText
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatArea
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatBedrooms
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatPrice
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatProfessional
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatRooms
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * ViewModel for AdsListScreen
 */
class AdsListViewModel : ViewModel() {

    var adsInfoList : List<Map<AdElements, UiText>> by mutableStateOf(mutableListOf())
    var errorMessage: String by mutableStateOf("")
    var isAdsFetchingInProgress by mutableStateOf(true)

    /**
     * Fetches ads list from the server API
     */
    fun getAdsList() {
        viewModelScope.launch {
            isAdsFetchingInProgress = true
            errorMessage = ""

            val apiService = ApiService.getInstance()
            var adsListResponse = listOf<Ad>()
            try {
                adsListResponse = apiService.getAdsItems().items
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }

            val list = mutableListOf<Map<AdElements, UiText>>()

            for (ad in adsListResponse) {
                list.add(getInfoToDisplayFrom(ad))
            }

            isAdsFetchingInProgress = false

            adsInfoList = list
        }
    }

    /**
     * Create Map<AdElements, UiText> from [ad] to dynamically get info to display in the UI
     */
    private fun getInfoToDisplayFrom(ad: Ad): Map<AdElements, UiText> {

        val infoToDisplay = mutableMapOf<AdElements, UiText>()

        infoToDisplay[AdElements.IMAGE_URL] = UiText.String(ad.url)

        infoToDisplay[AdElements.PRICE] = ad.price.formatPrice()
        ad.propertyType?.let {
            infoToDisplay[AdElements.PROPERTY_TYPE] = UiText.String(it)
        }

        val specifications = mutableListOf<UiText>()
        ad.area?.let {
            specifications.add(it.formatArea())
        }
        ad.rooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.String(";"))
            }
            specifications.add(it.formatRooms())
        }
        ad.bedrooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.String(";"))
            }
            specifications.add(it.formatBedrooms())
        }
        if(specifications.isNotEmpty()) {
            infoToDisplay[AdElements.SPECIFICATIONS] = UiText.CombinedStrings(specifications)
        }

        ad.city?.let {
            infoToDisplay[AdElements.CITY] = UiText.String(it)
        }

        ad.professional?.let {
            infoToDisplay[AdElements.PROFESSIONAL] = it.formatProfessional()
        }

        return infoToDisplay
    }
}