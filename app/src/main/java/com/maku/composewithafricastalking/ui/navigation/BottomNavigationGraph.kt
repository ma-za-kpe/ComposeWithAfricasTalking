package com.maku.composewithafricastalking.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.maku.composewithafricastalking.ui.screens.info.navigation.InfoScreen
import com.maku.composewithafricastalking.ui.screens.info.navigation.infoNavigationRoute
import com.maku.composewithafricastalking.ui.navigation.SenatorScreen
import com.maku.composewithafricastalking.ui.navigation.senatorNavigationRoute

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
            },
            modifier
        )

        InfoScreen(
            startDestination = infoNavigationRoute
        )
        SenatorScreen(
            startDestination = senatorNavigationRoute,
            modifier
        )
    }
}
