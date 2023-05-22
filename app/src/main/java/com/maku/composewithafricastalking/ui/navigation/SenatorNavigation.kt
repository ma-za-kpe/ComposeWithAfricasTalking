package com.maku.composewithafricastalking.ui.screens.senator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.maku.senator.ui.SenatorRoute

const val senatorNavigationRoute = "senator_route"
const val senatorGraphNavigationDestination = "senator_destination_route"

fun NavController.navigateToSenator(navOptions: NavOptions? = null) {
    this.navigate(senatorGraphNavigationDestination, navOptions)
}

fun NavGraphBuilder.SenatorScreen(
    startDestination: String
) {
    navigation(
        route = senatorGraphNavigationDestination,
        startDestination = startDestination
    ) {
        composable(route = startDestination) {
            SenatorRoute()
        }
    }
}
