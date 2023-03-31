package com.maku.composewithafricastalking.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maku.core.ui.theme.ComposeWithAfricasTalkingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.maku.core.ui.theme.ComposeWithAfricasTalkingTheme {
                ATComposeApp()
            }
        }
    }
}
