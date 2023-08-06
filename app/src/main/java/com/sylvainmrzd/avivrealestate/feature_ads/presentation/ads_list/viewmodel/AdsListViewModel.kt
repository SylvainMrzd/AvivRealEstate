package com.sylvainmrzd.avivrealestate.feature_ads.presentation.ads_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sylvainmrzd.avivrealestate.feature_ads.domain.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.BaseViewModel
import com.sylvainmrzd.avivrealestate.others.Status
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.UiText
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.ads_list.AdsState
import com.sylvainmrzd.avivrealestate.others.Constants
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
) : BaseViewModel() {

    //region Variables
    override var hasBackButton = false
    override var hasFilterByPropertyTypeAction = true

    var errorMessage: String? by mutableStateOf("")
    var isAdsFetchingInProgress by mutableStateOf(true)

    private val _state = mutableStateOf(AdsState())
    val state: State<AdsState> = _state

    private val _ads = MutableLiveData<Event<Resource<Items>>>()
    val ads: LiveData<Event<Resource<Items>>> = _ads
    //endregion

    //region Init
    init {
        fetchAdsList()
    }
    //endregion

    //region Data fetching
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

                            if (propertyTypeFilterSelectedOption.isNotEmpty()) {
                                adsListItems = adsListItems.filter {ad ->
                                    ad.propertyType == propertyTypeFilterSelectedOption
                                }
                                if (adsListItems.isEmpty()) {
                                    errorMessage = Constants.FILTERED_LIST_EMPTY_ERROR
                                }
                            }

                        }
                    }
                }

                val list = mutableListOf<Map<AdElements, UiText>?>()

                for (ad in adsListItems) {
                    list.add(ad.infoToDisplay)
                }

                _state.value = state.value.copy(ads = list)
            }
        }
    }
    //endregion

    //region Data updates
    override fun updatePropertyTypeFilter(filter: String) {
        super.updatePropertyTypeFilter(filter)
        fetchAdsList()
    }
    //endregion
}