package com.maku.feature.airtime.airtime.other

import androidx.compose.runtime.mutableStateOf
import com.maku.core.ui.R
import com.maku.core.ui.util.isValidAmount
import com.maku.core.ui.util.isValidPhone
import com.maku.core.ui.vm.MainViewModel
import com.maku.feature.airtime.data.uiState.ForAnotherAirtimeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor() : MainViewModel() {
    var forAnotherUiState = mutableStateOf(ForAnotherAirtimeUiState())
        private set

    private val anotherAmount
        get() = forAnotherUiState.value.amount

    private val anotherPhone
        get() = forAnotherUiState.value.phone

    fun onAnotherAmountChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(amount = newValue)
    }

    fun onAnotherPhoneChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(phone = newValue)
    }

    fun onBuyAirtimeForAnotherClick() {
        forAnotherUiState.value = forAnotherUiState.value.copy(loading = true)
        if (!anotherPhone.isValidPhone()) {
            forAnotherUiState.value = forAnotherUiState.value.copy(loading = false, error = true)
            com.maku.core.ui.util.snackbar.SnackbarManager.showMessage(R.string.phone_error)
            return
        }

        if (!anotherAmount.isValidAmount()) {
            forAnotherUiState.value = forAnotherUiState.value.copy(loading = false, error = true)
            com.maku.core.ui.util.snackbar.SnackbarManager.showMessage(R.string.amount_error)
            return
        }

        launchCatching {
            com.maku.core.ui.util.snackbar.SnackbarManager.showMessage("Coming very soon")
        }
    }
}
