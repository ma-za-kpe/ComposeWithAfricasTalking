package com.maku.composewithafricastalking.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.maku.core.ui.R
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    AIRTIME(
        selectedIcon = Default.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        iconTextId = R.string.airtime,
        titleTextId = R.string.airtime
    ),
    INFO(
        selectedIcon = Icons.Outlined.Info,
        unselectedIcon = Icons.Outlined.Info,
        iconTextId = R.string.info,
        titleTextId = R.string.info
    ),
    SENATOR(
        selectedIcon = Icons.Outlined.ThumbUp,
        unselectedIcon = Icons.Outlined.ThumbUp,
        iconTextId = R.string.senator,
        titleTextId = R.string.senator
    )
}
