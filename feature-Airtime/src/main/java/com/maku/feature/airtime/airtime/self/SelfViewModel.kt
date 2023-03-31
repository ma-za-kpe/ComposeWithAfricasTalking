package com.maku.featureairtime.airtime.self

import androidx.compose.runtime.mutableStateOf
import com.maku.core.ui.R
import com.maku.core.ui.util.isValidAmount
import com.maku.core.ui.vm.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelfViewModel @Inject constructor() : MainViewModel() {
    var uiState = mutableStateOf(com.maku.featureairtime.data.uiState.ForSelfAirtimeUiState())
        private set

    private val myAmount
        get() = uiState.value.amount

    fun onMyAmountChange(newValue: String) {
        uiState.value = uiState.value.copy(amount = newValue)
    }

    fun onBuyAirtimeClick() {
        uiState.value = uiState.value.copy(loading = true)
        if (!myAmount.isValidAmount()) {
            uiState.value = uiState.value.copy(loading = false, error = true)
            com.maku.core.ui.util.snackbar.SnackbarManager.showMessage(R.string.amount_error) // TODO: use the error messages that come from the material library instead
            return
        }

        launchCatching {
            com.maku.core.ui.util.snackbar.SnackbarManager.showMessage("Coming very soon")
        }
    }
}
