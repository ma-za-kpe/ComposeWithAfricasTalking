package com.maku.composewithafricastalking.ui.screens.airtime.other

import androidx.compose.runtime.mutableStateOf
import com.maku.composewithafricastalking.R
import com.maku.core.data.repo.ATComposeRepository
import com.maku.composewithafricastalking.core.util.isValidAmount
import com.maku.composewithafricastalking.core.util.isValidPhone
import com.maku.composewithafricastalking.core.util.snackbar.SnackbarManager
import com.maku.composewithafricastalking.ui.data.uiState.ForAnotherAirtimeUiState
import com.maku.composewithafricastalking.ui.data.uiState.ForSelfAirtimeUiState
import com.maku.composewithafricastalking.ui.vm.MainViewModel
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
            SnackbarManager.showMessage(R.string.phone_error)
            return
        }

        if (!anotherAmount.isValidAmount()) {
            forAnotherUiState.value = forAnotherUiState.value.copy(loading = false, error = true)
            SnackbarManager.showMessage(R.string.amount_error)
            return
        }

        launchCatching {
            SnackbarManager.showMessage("Coming very soon")
        }
    }
}
