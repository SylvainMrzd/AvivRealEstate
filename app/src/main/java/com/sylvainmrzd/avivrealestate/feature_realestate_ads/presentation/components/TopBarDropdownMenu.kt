package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.others.Constants

/**
 * A DropdownMenu to be used for TopAppbar actions
 */
@Composable
fun TopBarDropdownMenu(
    expanded: Boolean,
    optionsList: List<String>,
    onDismissRequest: () -> Unit,
    onItemClick: (String) -> Unit
) {
    var selectedOption: String by remember {
        mutableStateOf("")
    }

    DropdownMenu(
        modifier = Modifier.testTag(Constants.TOP_BAR_DROPDOWN_MENU_TAG),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        for (option in optionsList) {
            val checked by remember {
                derivedStateOf {
                    option == selectedOption
                }
            }

            DropdownMenuItem(
                modifier = Modifier.testTag(Constants.TOP_BAR_DROPDOWN_MENU_OPTION_TAG.plus(option)),
                text = {
                    Text(
                        modifier = Modifier.padding(end = 24.dp),
                        text = option
                    )
                },
                onClick = {
                    selectedOption = if (!checked) {
                        option
                    } else {
                        ""
                    }
                    onItemClick(selectedOption)
                },
                trailingIcon = {
                    if (checked) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.check_icon_content_description)
                        )
                    }
                }
            )
        }
    }
}