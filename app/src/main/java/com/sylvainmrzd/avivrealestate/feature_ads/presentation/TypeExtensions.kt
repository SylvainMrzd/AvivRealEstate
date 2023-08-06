package com.sylvainmrzd.avivrealestate.feature_ads.presentation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.sylvainmrzd.avivrealestate.R
import com.sylvainmrzd.avivrealestate.feature_ads.presentation.util.UiText
import java.text.DecimalFormat

/**
 * Format a price Float into a [UiText.StringResource]
 */
fun Float.formatPrice(): UiText.StringResource {
    val priceFormat = DecimalFormat("###,###,###,###,###")
    return UiText.StringResource(
        R.string.price,
        priceFormat.format(this).replace(",", " ")
    )
}

/**
 * Format a price by square meter into a [UiText.StringResource]
 */
fun Int.formatSquareMeterPrice() : UiText.StringResource {
    return UiText.StringResource(
        R.string.square_meter_price,
        this
    )
}

/**
 * Format a area Float into a [UiText.StringResource]
 */
fun Float.formatArea(): UiText.StringResource {
    return UiText.StringResource(
        R.string.square_meter_unit,
        this.toString()
    )
}

/**
 * Format a rooms Int into a [UiText.StringResource]
 */
fun Int.formatRooms(): UiText.StringResource {
    return UiText.StringResource(
        resId =
            when(this) {
                1 -> R.string.one_room
                else -> R.string.rooms
            },
        this.toString()
    )
}

/**
 * Format a bedrooms Int into a [UiText.StringResource]
 */
fun Int.formatBedrooms(): UiText.StringResource {
    return UiText.StringResource(
        resId =
            when(this) {
                1 -> R.string.one_bedroom
                else -> R.string.bedrooms
            },
        this.toString()
    )
}

/**
 * Format a professional into a [UiText.StringResource]
 */
fun String.formatProfessional(): UiText.StringResource {
    return UiText.StringResource(
        R.string.being_sold_by,
        this
    )
}

/**
 * Adds shimmerEffect() function to Modifier class by extension to make possible to add a shimmer
 * effect to any composable object through its modifier
 */
fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmerEffectTransition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1500)
        ), label = "shimmerEffectAnimation"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xB2B8B3B3),
                Color(0xB3757272),
                Color(0xB3B8B3B3)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}