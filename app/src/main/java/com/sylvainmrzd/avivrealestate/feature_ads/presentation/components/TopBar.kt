package com.sylvainmrzd.avivrealestate.feature_ads.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.BaseViewModel
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.PropertyTypes
import com.sylvainmrzd.avivrealestate.others.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = stringResource(id = R.string.app_name),
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: BaseViewModel,
    onBackButtonClick: () -> Unit = {}
) {
    var filterDropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier.testTag(Constants.TOP_BAR_TAG),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (viewModel.hasBackButton) {
                IconButton(
                    modifier = Modifier.testTag(Constants.BACK_BUTTON_TAG),
                    onClick = onBackButtonClick
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        stringResource(R.string.back_icon_content_description)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            when {
                viewModel.hasFilterByPropertyTypeAction -> {
                    Box(modifier = Modifier.testTag(Constants.TOP_BAR_FILTER_ACTION_TAG)) {
                        IconButton(onClick = { filterDropDownMenuExpanded = true }) {
                            Icon(
                                painter = painterResource(id = viewModel.propertyTypeFilterActionIconId),
                                contentDescription = stringResource(R.string.filtrer_icon_content_description)
                            )
                        }

                        val optionsList = PropertyTypes.values().map { it.value }

                        TopBarDropdownMenu(
                            expanded = filterDropDownMenuExpanded,
                            optionsList = optionsList,
                            onDismissRequest = { filterDropDownMenuExpanded = false },
                        ) {
                            viewModel.updatePropertyTypeFilter(it)
                            filterDropDownMenuExpanded = false
                        }
                    }
                }
            }
        }
    )
}
