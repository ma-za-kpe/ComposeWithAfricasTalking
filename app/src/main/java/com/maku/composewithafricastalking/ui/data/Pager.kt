package com.maku.composewithafricastalking.ui.data

import androidx.compose.runtime.Composable
import com.maku.composewithafricastalking.ui.screens.airtime.other.BuyForOtherScreen
import com.maku.composewithafricastalking.ui.screens.airtime.self.BuyForSelfScreen

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

