package com.sylvainmrzd.avivrealestate.feature_realestate_ads.domain.model

import android.content.Context
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatArea
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatBedrooms
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatPrice
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatProfessional
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.formatRooms
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.AdElements
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.util.UiText
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private const val PRICE_STRING = "10 000 €"
private const val AREA_STRING = "30 m²"
private const val ROOMS_STRING = "2 rooms"
private const val BEDROOMS_STRING = "1 bedroom"
private const val PROFESSIONAL_STRING = "Being sold by SeLoger"

@RunWith(MockitoJUnitRunner::class)
class AdTest {

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `transform Ad into a Map of AdElements and UiText`() {
        val mockContext = mock<Context> {
            on { getString(R.string.price) } doReturn PRICE_STRING
            on { getString(R.string.square_meter_unit) } doReturn AREA_STRING
            on { getString(R.string.rooms) } doReturn ROOMS_STRING
            on { getString(R.string.bedrooms) } doReturn BEDROOMS_STRING
            on { getString(R.string.being_sold_by) } doReturn PROFESSIONAL_STRING
        }

        val expectedMap = mutableMapOf<AdElements, UiText>()

        expectedMap[AdElements.IMAGE_URL] =
            UiText.StringValue("https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg")
        expectedMap[AdElements.PRICE] = 10000f.formatPrice()
        expectedMap[AdElements.PROPERTY_TYPE] = UiText.StringValue("Apartment")
        expectedMap[AdElements.SPECIFICATIONS] =
            UiText.CombinedStrings(
                listOf(
                    30.0f.formatArea(),
                    UiText.StringValue(";"),
                    2.formatRooms(),
                    UiText.StringValue(";"),
                    1.formatBedrooms()
                )
            )
        expectedMap[AdElements.CITY] = UiText.StringValue("London")
        expectedMap[AdElements.PROFESSIONAL] = "SeLoger".formatProfessional()


        val ad = Ad(
            1,
            "London",
            999,
            30f,
            "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            10000.00f,
            "SeLoger",
            "Apartment",
            1,
            2
        )

        val testedMap = ad.infoToDisplay

        assertEquals(
            expectedMap[AdElements.IMAGE_URL]?.asStringWith(mockContext),
            testedMap[AdElements.IMAGE_URL]?.asStringWith(mockContext)
        )
        assertEquals(
            expectedMap[AdElements.PRICE]?.asStringWith(mockContext),
            testedMap[AdElements.PRICE]?.asStringWith(mockContext)
        )
        assertEquals(
            expectedMap[AdElements.PROPERTY_TYPE]?.asStringWith(mockContext),
            testedMap[AdElements.PROPERTY_TYPE]?.asStringWith(mockContext)
        )
        assertEquals(
            expectedMap[AdElements.SPECIFICATIONS]?.asStringWith(mockContext),
            testedMap[AdElements.SPECIFICATIONS]?.asStringWith(mockContext)
        )
        assertEquals(
            expectedMap[AdElements.CITY]?.asStringWith(mockContext),
            testedMap[AdElements.CITY]?.asStringWith(mockContext)
        )
        assertEquals(
            expectedMap[AdElements.PROFESSIONAL]?.asStringWith(mockContext),
            testedMap[AdElements.PROFESSIONAL]?.asStringWith(mockContext)
        )
    }
}