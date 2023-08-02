package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sylvainmrzd.avivrealestate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = stringResource(id = R.string.app_name),
    hasBackButton: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackButtonClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}