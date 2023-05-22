package com.maku.senator.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun SenatorRoute(
    modifier: Modifier = Modifier
) {

   SenatorScreen()
}

@Composable
fun SenatorScreen() {
    Text("makuuuuu")
}

@Composable
@Preview(
    name = "phone",
    uiMode = 32,
    showSystemUi = true,
    showBackground = false,
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
fun SenatorRoutePreview() {
    SenatorScreen()
}
