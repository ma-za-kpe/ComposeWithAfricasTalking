package com.maku.composewithafricastalking.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.maku.composewithafricastalking.ui.state.AppState

@Composable
fun MainNavGraph(
    appState: AppState,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination
    ) {
        BottomNavigationGraph(
            startDestination = atcomposeGraphNavigationDestination,
            modifier = modifier
        )
    }
}
