package com.maku.composewithafricastalking.ui.state

import android.content.res.Resources
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maku.composewithafricastalking.core.navigation.TopLevelDestination
import com.maku.composewithafricastalking.core.util.snackbar.SnackbarManager
import com.maku.composewithafricastalking.core.util.snackbar.SnackbarMessage.Companion.toMessage
import com.maku.composewithafricastalking.ui.screens.atcompose.navigation.atcomposeNavigationRoute
import com.maku.composewithafricastalking.ui.screens.atcompose.navigation.navigateToATCompose
import com.maku.composewithafricastalking.ui.screens.info.navigation.infoNavigationRoute
import com.maku.composewithafricastalking.ui.screens.info.navigation.navigateToInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): AppState {
    return remember(
        navController,
        snackbarManager,
        resources,
        coroutineScope,
        systemUiController,
        snackbarHostState
    ) {
        AppState(
            navController,
            snackbarManager,
            resources,
            coroutineScope,
            systemUiController,
            snackbarHostState
        )
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Stable
class AppState(
    val navController: NavHostController,
    val snackbarManager: SnackbarManager,
    val resources: Resources,
    coroutineScope: CoroutineScope,
    val systemUiController: SystemUiController,
    val snackbarHostState: SnackbarHostState,
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                snackbarHostState.showSnackbar(text)
            }
        }
    }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            atcomposeNavigationRoute -> TopLevelDestination.AIRTIME
            infoNavigationRoute -> TopLevelDestination.INFO
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.AIRTIME -> navController.navigateToATCompose(topLevelNavOptions)
            TopLevelDestination.INFO -> navController.navigateToInfo(topLevelNavOptions)
            else -> {}
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}