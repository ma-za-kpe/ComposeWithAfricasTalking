package com.maku.airtime.ui.self

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

    fun onBuyAirtimeClick() {
        uiState.value = uiState.value.copy(loading = true)
//        if (!myAmount.isValidAmount()) {
//            uiState.value = uiState.value.copy(
//                loading = false,
//                error = true
//            )
//            // TODO: use the error messages that come from the material library instead
//            SnackbarManager.showMessage(R.string.amount_error)
//            return
//        }

        launchCatching {
            SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Coming very soon"))
        }
    }
}
