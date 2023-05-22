package com.maku.core.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.core.util.snackbar.SnackbarManager
import com.maku.core.util.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Add more log services here, e.g firebase crashlytics, etc
 * */
open class MainViewModel() : ViewModel() {
    fun launchCatching(
        snackbar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            if (snackbar) {
                SnackbarManager.showMessage(throwable.toSnackbarMessage())
            }
        },
        block = block
    )
}
