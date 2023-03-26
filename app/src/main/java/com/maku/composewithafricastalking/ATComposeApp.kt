package com.maku.composewithafricastalking

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maku.composewithafricastalking.core.navigation.MainNavGraph
import com.maku.composewithafricastalking.core.navigation.bottomNavigationRoute
import com.maku.composewithafricastalking.ui.screens.bottombar.ATComposeBottomBar
import com.maku.composewithafricastalking.ui.state.rememberAppState
import com.maku.composewithafricastalking.ui.theme.ComposeWithAfricasTalkingTheme

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
    ComposeWithAfricasTalkingTheme {
        ATComposeApp()
    }
}