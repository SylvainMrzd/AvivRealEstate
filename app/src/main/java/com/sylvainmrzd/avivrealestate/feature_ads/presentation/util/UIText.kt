package com.sylvainmrzd.avivrealestate.feature_ads.presentation.util

import android.content.Context
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
    data class StringValue(val value: String?): UiText()
    data class IntValue(val value: Int): UiText()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText()

    class CombinedStrings(
        val resList: List<UiText>
    ): UiText()

    @Composable
    fun asString(): String? {
        return when(this) {
            is StringValue -> value
            is StringResource -> stringResource(resId, *args)
            is CombinedStrings -> {
                var combinedString = ""

                for (res in resList) {
                    combinedString += res.asString()
                }

                return combinedString
            }
            else -> null
        }
    }

    fun asStringWith(context: Context): String? {
        return when(this) {
            is StringValue -> value
            is StringResource -> context.getString(resId)
            is CombinedStrings -> {
                var combinedString = ""

                for (res in resList) {
                    combinedString += res.asStringWith(context)
                }

                return combinedString
            }
            else -> null
        }
    }

    fun asInt(): Int? {
        return when(this) {
            is IntValue -> value
            else -> null
        }
    }
}