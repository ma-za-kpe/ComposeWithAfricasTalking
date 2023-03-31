package com.maku.composewithafricastalking.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.maku.composewithafricastalking.ui.screens.info.navigation.InfoScreen
import com.maku.composewithafricastalking.ui.screens.info.navigation.infoNavigationRoute

const val bottomNavigationRoute = "bottom_nav_route"

fun NavGraphBuilder.BottomNavigationGraph(
    startDestination: String,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = startDestination,
        route = bottomNavigationRoute
    ) {
        ATComposeScreen(
            startDestination = atcomposeNavigationRoute,
            nestedGraphs = {
//                AirtimeSuccess(
//                    onBackClick = { appState.popUp() }
//                )
            },
            modifier
        )

        InfoScreen(
            startDestination = infoNavigationRoute
        )
    }
}
