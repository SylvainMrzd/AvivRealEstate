package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository.AvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.BaseViewModel
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.UiText
import com.sylvainmrzd.avivrealestate.others.Event
import com.sylvainmrzd.avivrealestate.others.Resource
import com.sylvainmrzd.avivrealestate.others.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for AdDetailListScreen
 */
@HiltViewModel
class AdDetailViewModel @Inject constructor(
    private val avivRepository: AvivRealEstateRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    override var hasBackButton = true
    override var hasFilterByPropertyTypeAction = false

    var adInfo: Map<AdElements, UiText>? by mutableStateOf(mutableMapOf())
    var errorMessage: String? by mutableStateOf("")
    var isAdDetailFetchingInProgress by mutableStateOf(true)

    private val _adDetail = MutableLiveData<Event<Resource<Ad>>>()
    val adDetail: LiveData<Event<Resource<Ad>>> = _adDetail

    var adId: Int = -1

    init {
        savedStateHandle.get<Int>("adId")?.let { id ->
            if(id != -1) {
                adId = id
                fetchAdDetail(adId)
            }
        }
    }

    /**
     * Fetches ad detail from the server API
     */
    fun fetchAdDetail(id: Int) {
        viewModelScope.launch {
            isAdDetailFetchingInProgress = true
            errorMessage = ""

            val adDetailResponse = avivRepository.loadAdDetail(id)
            _adDetail.value = Event(adDetailResponse)

            _adDetail.value?.getContentIfNotHandled()?.let { resource ->
                when(resource.status) {
                    Status.ERROR -> {
                        isAdDetailFetchingInProgress = false
                        errorMessage = resource.message
                    }
                    Status.LOADING -> {
                        isAdDetailFetchingInProgress = true
                    }
                    Status.SUCCESS -> {
                        isAdDetailFetchingInProgress = false
                        resource.data?.let {
                            adInfo = it.infoToDisplay
                        }
                    }
                }
            }
        }
    }
}