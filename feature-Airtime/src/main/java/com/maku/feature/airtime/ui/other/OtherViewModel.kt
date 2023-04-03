package com.maku.feature.airtime.ui.other

import com.maku.feature.airtime.domain.usecase.SendAirtime as SendAirtimeUsecase
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.maku.core.network.state.ApiResult
import com.maku.core.ui.R
import com.maku.core.ui.util.isValidAmount
import com.maku.core.ui.util.isValidPhone
import com.maku.core.ui.vm.MainViewModel
import com.maku.feature.airtime.data.uiState.ForAnotherAirtimeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val sendAirtime: SendAirtimeUsecase
) : MainViewModel() {
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

        // TODO: localise this
        // TODO: detect or find a way to get client telecom lines per country ug and ke for now
        val phone = "+256${if (anotherPhone[0] == '0') anotherPhone.drop(1) else anotherPhone}"

        launchCatching {
            // TODO: make this airtime sending logic global only for vms
            val list = ArrayList<MutableMap<String, String>>()
            val recipient: MutableMap<String, String> = mutableMapOf()
            recipient["phoneNumber"] = "$phone"
            recipient["amount"] = "UGX $anotherAmount"
            list.add(recipient)
            val json = Gson().toJson(list)

            // TODO: secure these keys and logic for both sandbox and , for the different countries
            val response = sendAirtime(
                "0cc95e7aa1417164dd9608fd0bda450ef0552eb42f95e038c7226139758205fb",
                "sandbox", //
                json
            )

            if (response.isSuccessful) {
                val body = response.body()!!
                // TODO: do something with state
                ApiResult.Success(body)
                Log.d("TAG", "onBuyAirtimeForAnotherClick: list ${body.responses} ")
            } else {
                throw Exception(response.errorBody()?.charStream()?.readText())
            }
        }
    }
}
