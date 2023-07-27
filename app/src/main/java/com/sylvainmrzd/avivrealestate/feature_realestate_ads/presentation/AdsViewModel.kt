package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source.ApiService
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import kotlinx.coroutines.launch
import java.lang.Exception

class AdsViewModel : ViewModel() {

    var adsListResponse : List<Ad> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getAdsList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                adsListResponse = apiService.getAdsItems().items
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}