package com.maku.airtime.ui.self

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.maku.airtime.data.uiState.ForSelfAirtimeUiState
import com.maku.core.ui.vm.MainViewModel
import com.maku.core.util.snackbar.SnackbarManager
import com.maku.core.util.snackbar.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelfViewModel @Inject constructor() : MainViewModel() {
    var uiState = mutableStateOf(ForSelfAirtimeUiState())
        private set

    private val myAmount
        get() = uiState.value.amount

    fun onMyAmountChange(newValue: String) {
        uiState.value = uiState.value.copy(amount = newValue)
    }

    val myAmountHasLocalError by derivedStateOf {
        isAmountCorrect(
            myAmount,
            listOf() // set this after implementing the auth
        )
    }

    fun onBuyAirtimeClick(amountError: Boolean,) {
        if (amountError) {
            SnackbarManager.showMessage(
                SnackbarMessage.StringSnackbar("Please fix the issues in the form.")
            )
            return
        }
        launchCatching {
            uiState.value = uiState.value.copy(loading = true)
            SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Coming very soon"))
        }
    }
}
