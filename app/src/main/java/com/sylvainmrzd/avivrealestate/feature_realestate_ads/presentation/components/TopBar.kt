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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.others.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = stringResource(id = R.string.app_name),
    hasBackButton: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackButtonClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.testTag(Constants.TOP_BAR_TAG),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(
                    modifier = Modifier.testTag(Constants.BACK_BUTTON_TAG),
                    onClick = onBackButtonClick
                ) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}