package com.maku.composewithafricastalking.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maku.composewithafricastalking.ui.bottombar.ATComposeBottomBar
import com.maku.composewithafricastalking.ui.navigation.MainNavGraph
import com.maku.composewithafricastalking.ui.navigation.bottomNavigationRoute
import com.maku.composewithafricastalking.ui.state.rememberAppState

@Composable
fun ATComposeApp() {
    val appState = rememberAppState()
    androidx.compose.material3.Scaffold(
        bottomBar = {
            ATComposeBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination =
                appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackBarData ->
                    Snackbar(
                        snackBarData,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                }
            )
        }
    ) {
        MainNavGraph(
            appState = appState,
            startDestination = bottomNavigationRoute,
            modifier = Modifier.padding(it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ATComposeAppPreview() {
    com.maku.core.ui.theme.ComposeWithAfricasTalkingTheme {
        ATComposeApp()
    }
}
