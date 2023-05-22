package com.maku.core.ui.vm

import androidx.core.text.isDigitsOnly
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

    // TODO: move strings to strings.xml file
    fun isAmountCorrect(amountValue: String, limit: List<String>): Pair<Boolean, String> {
        if (amountValue.isEmpty()) {
            return Pair(true, "amount cannot be empty")
        } else if (!amountValue.isDigitsOnly()) {
            return Pair(true, "amount must only be numbers")
        } else if (
            amountValue.toInt() !in
            (limit[0].trim().toDouble().toInt() + 1)..limit[1].trim().toInt()
        ) {
            return Pair(true, "amount must be between $limit")
        }
        return Pair(false, "")
    }
}
