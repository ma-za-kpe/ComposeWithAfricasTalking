package com.maku.airtime.data

import androidx.compose.runtime.Composable
import com.maku.airtime.ui.other.BuyForOtherScreen
import com.maku.airtime.ui.self.BuyForSelfScreen

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
