package com.sylvainmrzd.avivrealestate

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * Allows to wait [timeoutMillis] seconds in UI tests before continuing the test execution
 */
fun ComposeContentTestRule.waitUntilTimeout(
    timeoutMillis: Long
) {
    AsyncTimer.start(timeoutMillis)
    this.waitUntil(
        condition = { AsyncTimer.expired },
        timeoutMillis = timeoutMillis + 1000
    )
}

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000) {
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}