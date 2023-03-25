package com.maku.composewithafricastalking.ui.screens.atcompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.maku.composewithafricastalking.ui.screens.atcompose.ATComposeRoute

const val atcomposeNavigationRoute = "airtime_compose_route"
const val atcomposeGraphNavigationDestination = "airtime_compose_destination_route"

fun NavController.navigateToATCompose(navOptions: NavOptions? = null) {
    this.navigate(atcomposeGraphNavigationDestination, navOptions)
}

fun NavGraphBuilder.ATComposeScreen(
    startDestination: String,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    modifier: Modifier = Modifier
) {
    navigation(
        route = atcomposeGraphNavigationDestination,
        startDestination = startDestination
    ) {
        composable(route = startDestination) {
            ATComposeRoute(
                modifier
            )
        }
        nestedGraphs() // TODO: look into this more ...
    }
}
