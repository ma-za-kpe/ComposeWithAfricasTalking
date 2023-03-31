package com.maku.feature.airtime.data

import androidx.compose.runtime.Composable
import com.maku.feature.airtime.airtime.other.BuyForOtherScreen
import com.maku.feature.airtime.airtime.self.BuyForSelfScreen

val tabRowItems = listOf(
    TabRowItem(
        title = "For Another",
        screen = {
            BuyForOtherScreen()
        }
    ),
    TabRowItem(
        title = "For Self",
        screen = {
            BuyForSelfScreen()
        }
    )
)

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit
)
