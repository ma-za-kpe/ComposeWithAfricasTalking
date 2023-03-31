package com.maku.composewithafricastalking.ui.screens.info.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val infoNavigationRoute = "info_route"
const val infoGraphNavigationDestination = "info_destination_route"

fun NavController.navigateToInfo(navOptions: NavOptions? = null) {
    this.navigate(infoGraphNavigationDestination, navOptions)
}

fun NavGraphBuilder.InfoScreen(
    startDestination: String
) {
    navigation(
        route = infoGraphNavigationDestination,
        startDestination = startDestination
    ) {
        composable(route = startDestination) {
            com.maku.composewithafricastalking.ui.screens.info.InfoScreen()
        }
    }
}
