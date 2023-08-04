package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sylvainmrzd.avivrealestate.R

open class BaseViewModel: ViewModel() {

    open var hasBackButton = false
    open var hasFilterByPropertyTypeAction = false
    open var propertyTypeFilterSelectedOption: String by mutableStateOf("")
    open var propertyTypeFilterActionIconId: Int by mutableStateOf(R.drawable.outline_filter_list_off)

    open fun updatePropertyTypeFilter(filter: String) {
        propertyTypeFilterSelectedOption = filter

        propertyTypeFilterActionIconId = if (filter.isNotEmpty()) {
            R.drawable.outline_filter_list
        } else {
            R.drawable.outline_filter_list_off
        }
    }
}