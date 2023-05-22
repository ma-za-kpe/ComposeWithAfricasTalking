package com.maku.airtime.ui.other

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.maku.airtime.data.uiState.ForAnotherAirtimeUiState
import com.maku.core.network.model.AirtimeLimitEntity
import com.maku.core.network.usecases.GetCountries
import com.maku.core.network.usecases.SendAirtime as SendAirtimeUsecase
import com.maku.core.state.ApiResult
import com.maku.core.ui.vm.MainViewModel
import com.maku.core.util.snackbar.SnackbarManager
import com.maku.core.util.snackbar.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@HiltViewModel
class OtherViewModel @Inject constructor(
    private val sendAirtime: SendAirtimeUsecase,
    private val getCountries: GetCountries
) : MainViewModel() {
    var forAnotherUiState = mutableStateOf(ForAnotherAirtimeUiState())
        private set

    private val anotherDialCode
        get() = forAnotherUiState.value.dial_code

    private val airtimeLimit
        get() = forAnotherUiState.value.airtimeLimit

    private val phone
        get() = forAnotherUiState.value.phone

    private val amount
        get() = forAnotherUiState.value.amount

    val userPhoneHasLocalError by derivedStateOf {
        isPhoneCorrect(phone)
    }

    val userAmountHasLocalError by derivedStateOf {
        isAmountCorrect(amount)
    }

    // TODO: find out how long phone numbers are in each country and validate accordingly
    private fun isPhoneCorrect(phoneValue: String): Pair<Boolean, String> {
        if (phoneValue.isEmpty()) {
            return Pair(true, "phone cannot be empty")
        } else if (!phoneValue.isDigitsOnly()) {
            return Pair(true, "phone must only be numbers")
        } else if (phoneValue.length < 10) {
            return Pair(true, "phone must be more that 10 characters")
        }
        return Pair(false, "")
    }

    private fun isAmountCorrect(amountValue: String): Pair<Boolean, String> {
        if (amountValue.isEmpty()) {
            return Pair(true, "amount cannot be empty")
        } else if (!amountValue.isDigitsOnly()) {
            return Pair(true, "amount must only be numbers")
        } else if (
            amountValue.toInt() !in
            (getLimit()[0].trim().toDouble().toInt() + 1)..getLimit()[1].trim().toInt()
        ) {
            return Pair(true, "amount must be between ${getLimit()}")
        }
        return Pair(false, "")
    }

    private fun getLimit(): List<String> {
        return airtimeLimit
            .substring(
                airtimeLimit.indexOf("(") + 1,
                airtimeLimit.indexOf(")")
            ).split("-")
    }

    fun onAnotherDialCodeChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(
            dial_code = newValue,
            airtimeLimitList = getAirtimeList(newValue),
            airtimeLimit = getAirtimeList(
                newValue
            )[0].name + " (" + getAirtimeList(newValue)[0].lower + " - " + getAirtimeList(
                newValue
            )[0].upper + ")"
        )
    }

    fun onAnotherAirtimeLimitChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(
            airtimeLimit = newValue
        )
    }

    fun onAnotherPhoneChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(
            phone = newValue
        )
    }

    fun onAnotherAmountChange(newValue: String) {
        forAnotherUiState.value = forAnotherUiState.value.copy(
            amount = newValue
        )
    }

    init {
        viewModelScope.launch {
            getCountries()
                .collect {
                    forAnotherUiState.value = forAnotherUiState.value.copy(
                        airtimeCountryList = it,
                        airtimeLimitList = getAirtimeList(anotherDialCode)
                    )
                }
        }
    }

    fun onBuyAirtimeForAnotherClick(amountError: Boolean, phoneError: Boolean) {
        if (amountError || phoneError) {
            SnackbarManager.showMessage(
                SnackbarMessage.StringSnackbar("Please fix the issues in the form.")
            )
            return
        }
        launchCatching {
            forAnotherUiState.value = forAnotherUiState.value.copy(loading = true)
            val validPhone =
                "${extractCode(anotherDialCode)}${if (phone[0] == '0') phone.drop(1) else phone}"
            Log.d("TAG", "onBuyAirtimeForAnotherClick: $validPhone")

            // TODO: temporary for one country, add logic for other live countries
            if (extractCode(anotherDialCode) != "+256") {
                SnackbarManager.showMessage(com.maku.core.ui.R.string.coming_to_country_soon)
                forAnotherUiState.value = forAnotherUiState.value.copy(loading = false)
            } else {
                // TODO: make this airtime sending logic global only for vms
                val list = ArrayList<MutableMap<String, String>>()
                val recipient: MutableMap<String, String> = mutableMapOf()
                recipient["phoneNumber"] = isValidPhoneFormat(phone, anotherDialCode)
                recipient["amount"] = "${extractCode(anotherDialCode)} $amount"
                list.add(recipient)
                val json = Gson().toJson(list)

                // TODO:secure these keys and logic for both sandbox and,for the different countries
                val sent = sendAirtime(
                    "fcb5afd02a20c9c640caa210ba458axxxxxxxxxxxxxxxxxxxxx38b54de98",
                    "easyAirtime",
                    json
                )

                when (sent) {
                    is ApiResult.Success -> {
                        SnackbarManager.showMessage(
                            SnackbarMessage.StringSnackbar(
                                "Success, Airtime sent to ${sent.data.numSent}"
                            )
                        )
                        forAnotherUiState.value = forAnotherUiState.value.copy(loading = false)
                    }
                    is ApiResult.Error -> handleError("${sent.code} ${sent.message}")
                    is ApiResult.Exception -> handleError("${sent.e.message}")
                }
            }
            forAnotherUiState.value = forAnotherUiState.value.copy(loading = false)
        }
    }

    private fun extractCode(dial_code: String) = dial_code.substringAfterLast(" ")

    private fun getAirtimeList(newValue: String): List<AirtimeLimitEntity> {
        val countryList = forAnotherUiState.value.airtimeCountryList.find {
            it.dialCode == extractCode(newValue)
        }
        return countryList?.airtimeLimits ?: emptyList()
    }

    private fun isValidPhoneFormat(
        phone: String,
        dial_code: String
    ): String {
        return "${extractCode(dial_code)}${if (phone[0] == '0') phone.drop(1) else phone}"
    }

    private fun handleError(error: String) {
        SnackbarManager.showMessage(SnackbarMessage.StringSnackbar(error))
        forAnotherUiState.value = forAnotherUiState.value.copy(loading = false)
    }
}
