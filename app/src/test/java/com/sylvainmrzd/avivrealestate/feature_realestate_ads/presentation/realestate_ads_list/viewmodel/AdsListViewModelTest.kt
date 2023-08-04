package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository.FakeAvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Items
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.PropertyTypes
import com.sylvainmrzd.avivrealestate.getOrAwaitValueTest
import com.sylvainmrzd.avivrealestate.others.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AdsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AdsListViewModel
    private var repository = FakeAvivRealEstateRepository()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `loading ads get an error`() {
        repository.setShouldReturnNetworkError(true)
        viewModel = AdsListViewModel(repository)
        val value = viewModel.ads.value

        assertEquals(value?.peekContent()?.status, Status.ERROR)
    }

    @Test
    fun `loading ads succeeds`() {
        repository.setShouldReturnNetworkError(false)
        viewModel = AdsListViewModel(repository)
        val value = viewModel.ads.value

        assertEquals(value?.peekContent()?.status, Status.SUCCESS)
    }

    @Test
    fun `fetch ads and response data is same as expected`(){
        val json = this.javaClass.classLoader?.getResource("listings.json")?.readText()
        val gson = Gson()

        val response: Items = gson.fromJson(json, Items::class.java)

        repository.setShouldMockRequest(true)
        viewModel = AdsListViewModel(repository)

        val actualResponse = viewModel.ads.getOrAwaitValueTest().peekContent().data

        assertEquals(response.toString(), actualResponse.toString())
    }

    @Test
    fun `don't have back button`(){
        viewModel = AdsListViewModel(repository)

        assertNotEquals(true, viewModel.hasBackButton)
    }

    @Test
    fun `propertyTypeFilterSelectedOption is empty by default`(){
        viewModel = AdsListViewModel(repository)

        assertEquals("", viewModel.propertyTypeFilterSelectedOption)
    }

    @Test
    fun `have filter button`(){
        viewModel = AdsListViewModel(repository)

        assertEquals(true, viewModel.hasFilterByPropertyTypeAction)
    }

    @Test
    fun `updatePropertyTypeFilter() change propertyTypeFilterSelectedOption value`(){
        viewModel = AdsListViewModel(repository)
        viewModel.updatePropertyTypeFilter(PropertyTypes.HOUSE_VILLA.value)

        assertEquals(PropertyTypes.HOUSE_VILLA.value, viewModel.propertyTypeFilterSelectedOption)
    }

    @Test
    fun `updatePropertyTypeFilter() change propertyTypeFilterActionIconId value`(){
        viewModel = AdsListViewModel(repository)
        assertEquals(R.drawable.outline_filter_list_off, viewModel.propertyTypeFilterActionIconId)

        viewModel.updatePropertyTypeFilter(PropertyTypes.HOUSE_VILLA.value)

        assertEquals(R.drawable.outline_filter_list, viewModel.propertyTypeFilterActionIconId)
    }
}