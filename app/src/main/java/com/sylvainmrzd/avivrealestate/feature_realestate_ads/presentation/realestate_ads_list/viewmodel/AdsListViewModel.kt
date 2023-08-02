package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.others.Status
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.UiText
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatArea
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatBedrooms
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatPrice
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatProfessional
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatRooms
import com.sylvainmrzd.avivrealestate.others.Event
import com.sylvainmrzd.avivrealestate.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for AdsListScreen
 */
@HiltViewModel
class AdsListViewModel @Inject constructor(
    private val avivRepository: AvivRealEstateRepository
) : ViewModel() {

    var adsInfoList : List<Map<AdElements, UiText>> by mutableStateOf(mutableListOf())
    var errorMessage: String? by mutableStateOf("")
    var isAdsFetchingInProgress by mutableStateOf(true)

    private val _ads = MutableLiveData<Event<Resource<Items>>>()
    val ads: LiveData<Event<Resource<Items>>> = _ads

    /**
     * Fetches ads list from the server API
     */
    fun fetchAdsList() {
        viewModelScope.launch {
            isAdsFetchingInProgress = true
            errorMessage = ""
            var adsListItems = listOf<Ad>()

            val adsListResponse = avivRepository.loadAdsList()
            _ads.value = Event(adsListResponse)

            _ads.value?.getContentIfNotHandled()?.let { resource ->
                when(resource.status) {
                    Status.ERROR -> {
                        isAdsFetchingInProgress = false
                        errorMessage = resource.message
                    }
                    Status.LOADING -> {
                        isAdsFetchingInProgress = true
                    }
                    Status.SUCCESS -> {
                        isAdsFetchingInProgress = false
                        resource.data?.let {
                            adsListItems = it.items
                        }
                    }
                }

                val list = mutableListOf<Map<AdElements, UiText>>()

                for (ad in adsListItems) {
                    list.add(getInfoToDisplayFrom(ad))
                }

                adsInfoList = list
            }
        }
    }

    /**
     * Create Map<AdElements, UiText> from [ad] to dynamically get info to display in the UI
     */
    private fun getInfoToDisplayFrom(ad: Ad): Map<AdElements, UiText> {

        val infoToDisplay = mutableMapOf<AdElements, UiText>()

        infoToDisplay[AdElements.IMAGE_URL] = UiText.StringValue(ad.url)

        infoToDisplay[AdElements.PRICE] = ad.price.formatPrice()
        ad.propertyType?.let {
            infoToDisplay[AdElements.PROPERTY_TYPE] = UiText.StringValue(it)
        }

        val specifications = mutableListOf<UiText>()
        ad.area?.let {
            specifications.add(it.formatArea())
        }
        ad.rooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.StringValue(";"))
            }
            specifications.add(it.formatRooms())
        }
        ad.bedrooms?.let {
            if (specifications.isNotEmpty()) {
                specifications.add(UiText.StringValue(";"))
            }
            specifications.add(it.formatBedrooms())
        }
        if(specifications.isNotEmpty()) {
            infoToDisplay[AdElements.SPECIFICATIONS] = UiText.CombinedStrings(specifications)
        }

        ad.city?.let {
            infoToDisplay[AdElements.CITY] = UiText.StringValue(it)
        }

        ad.professional?.let {
            infoToDisplay[AdElements.PROFESSIONAL] = it.formatProfessional()
        }

        return infoToDisplay
    }
}