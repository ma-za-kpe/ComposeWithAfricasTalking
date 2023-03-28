package com.maku.composewithafricastalking.ui.screens.airtime.self

import androidx.compose.runtime.mutableStateOf
import com.maku.composewithafricastalking.R
import com.maku.composewithafricastalking.core.data.repo.ATComposeRepository
import com.maku.composewithafricastalking.core.util.isValidAmount
import com.maku.composewithafricastalking.core.util.snackbar.SnackbarManager
import com.maku.composewithafricastalking.ui.data.uiState.ForSelfAirtimeUiState
import com.maku.composewithafricastalking.ui.vm.MainViewModel
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
        if (!myAmount.isValidAmount()) {
            uiState.value = uiState.value.copy(loading = false, error = true)
            SnackbarManager.showMessage(R.string.amount_error) // TODO: use the error messages that come from the material library instead
            return
        }

        launchCatching {
            SnackbarManager.showMessage("Coming very soon")
        }
    }
}
