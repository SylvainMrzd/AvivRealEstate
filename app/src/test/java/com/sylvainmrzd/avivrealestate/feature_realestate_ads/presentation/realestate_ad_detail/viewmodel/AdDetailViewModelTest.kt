package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ad_detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.repository.FakeAvivRealEstateRepository
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model.Ad
import com.sylvainmrzd.avivrealestate.getOrAwaitValueTest
import com.sylvainmrzd.avivrealestate.others.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class AdDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AdDetailViewModel
    private lateinit var savedStateHandle: SavedStateHandle
    private var repository = FakeAvivRealEstateRepository()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        savedStateHandle = SavedStateHandle()
        savedStateHandle["adId"] = 1
    }

    @Test
    fun `loading ad detail get an error`() {
        repository.setShouldReturnNetworkError(true)
        viewModel = AdDetailViewModel(repository, savedStateHandle)
        val value = viewModel.adDetail.value

        assertEquals(value?.peekContent()?.status, Status.ERROR)
    }

    @Test
    fun `loading ad detail succeeds`() {
        repository.setShouldReturnNetworkError(false)
        viewModel = AdDetailViewModel(repository, savedStateHandle)
        val value = viewModel.adDetail.value

        assertEquals(value?.peekContent()?.status, Status.SUCCESS)
    }

    @Test
    fun `fetch ad detail and response data is same as expected`(){
        val json = this.javaClass.classLoader?.getResource("ad_detail.json")?.readText()
        val gson = Gson()

        val response: Ad = gson.fromJson(json, Ad::class.java)

        repository.setShouldMockRequest(true)
        viewModel = AdDetailViewModel(repository, savedStateHandle)

        val actualResponse = viewModel.adDetail.getOrAwaitValueTest().peekContent().data

        assertEquals(response.toString(), actualResponse.toString())
    }
}