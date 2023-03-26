package com.maku.composewithafricastalking.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.maku.composewithafricastalking.ui.screens.atcompose.navigation.atcomposeGraphNavigationDestination
import com.maku.composewithafricastalking.ui.state.AppState

@Composable
fun MainNavGraph(
    appState: AppState,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
    ) {
        BottomNavigationGraph(
            startDestination = atcomposeGraphNavigationDestination,
            modifier = modifier
        )
    }
}

interface MainNavigationDestination {
    val route: String
    val destination: String
}
