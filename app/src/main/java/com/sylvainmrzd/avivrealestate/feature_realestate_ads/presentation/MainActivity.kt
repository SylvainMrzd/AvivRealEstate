package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.realestate_ads_list.view.AdsListScreen
import com.sylvainmrzd.avivrealestate.ui.theme.AvivRealEstateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvivRealEstateTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdsListScreen()
                }
            }
        }
    }
}