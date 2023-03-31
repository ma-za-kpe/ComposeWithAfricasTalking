package com.maku.core.ui.previews

import androidx.compose.ui.tooling.preview.Preview
// TODO: fix dark mode
/**
 * Multi-preview annotation that represents various device sizes. Add this annotation to a composable
 * to render various devices.
 */
@Preview
(
    name = "phone",
    uiMode = 16,
    showSystemUi = true,
    showBackground = false,
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)

@Preview
(
    name = "phone",
    uiMode = 32,
    showSystemUi = true,
    showBackground = false,
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)

annotation class DevicePreviews
