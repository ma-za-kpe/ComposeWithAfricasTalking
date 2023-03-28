package com.maku.composewithafricastalking.ui.screens.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.maku.composewithafricastalking.core.navigation.TopLevelDestination
import com.maku.composewithafricastalking.ui.state.rememberAppState
import com.maku.composewithafricastalking.ui.theme.ComposeWithAfricasTalkingTheme

@Composable
fun ATComposeBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    ATComposeNavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            ATComposeNavigationBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        else -> androidx.compose.material3.Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text(
                        stringResource(destination.iconTextId)
                    )
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true)
            ?: false // watch out for the naming of the routes here, it will not work as intented
    } ?: false

@Composable
fun ATComposeNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun RowScope.ATComposeNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = false
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AtComposeNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AtComposeNavigationDefaults.navigationContentColor(),
            selectedTextColor = AtComposeNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AtComposeNavigationDefaults.navigationContentColor(),
            indicatorColor = AtComposeNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ATComposeBottomBarPreview() {
    ComposeWithAfricasTalkingTheme {
        val appState = rememberAppState() // TODO: refactor this out of here
        ATComposeBottomBar(
            destinations = appState.topLevelDestinations,
            onNavigateToDestination = {},
            currentDestination = appState.currentDestination
        )
    }
}

object AtComposeNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
