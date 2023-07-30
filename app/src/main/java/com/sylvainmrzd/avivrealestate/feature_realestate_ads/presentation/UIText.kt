package com.sylvainmrzd.avivrealestate.feature_realestate_ads.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Sealed class used to pass text from ViewModel to UI
 * [String] is a String that returns its own value
 * [StringResource] returns a String built from a resource id and optional arguments
 * [CombinedStrings] returns a concatenated String built from multiple [String] and/or [StringResource]
 */
sealed class UiText {
    data class String(val value: kotlin.String?): UiText()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText()

    class CombinedStrings(
        val resList: List<UiText>
    ): UiText()

    @Composable
    fun asString(): kotlin.String? {
        return when(this) {
            is String -> value
            is StringResource -> stringResource(resId, *args)
            is CombinedStrings -> {
                var combinedString = ""

                for (res in resList) {
                    combinedString += res.asString()
                }

                return combinedString
            }
        }
    }
}