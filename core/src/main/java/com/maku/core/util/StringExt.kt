package com.maku.core.util

import android.util.Log
import com.google.gson.Gson


private const val MIN_PHONE_LENGTH = 10 // TODO: find out the minimum phone length per country

//fun isValidAmount(
//    amount: String,
//    dial_code: String = "+256",
//    telecom: String,
//): Boolean {
//    // Uganda
//    // Lower - UGX 50
//    // Upper - UGX 200,000
//    return amount.length >= getAirtimeLimitBoundsByCountry(
//        dial_code,
//        telecom
//    ).first || amount.length <= getAirtimeLimitBoundsByCountry(
//        dial_code,
//        telecom
//    ).second
//}

// TODO: reference => https://www.baeldung.com/kotlin/returning-multiple-values
//fun getAirtimeLimitBoundsByCountry(
//    dial_code: String = "+256",
//    telecom: String
//): Pair<Double, Double> {
//    var (lower, upper) = Pair(0.0, 0.0)
//    val list = getTelecomByCountryList(dial_code)
//    if (list?.size == 1) {
//        lower = list[0].lower
//        upper = list[0].upper.toDouble()
//    } else {
//        list?.forEach {
//            if (it.name == telecom){
//                lower = it.lower
//                upper = it.upper.toDouble()
//            }
//        }
//    }
//    return lower to upper
//}

//fun getTelecomByCountryList(dial_code: String): List<AirtimeLimit>? {
//    Log.d("TAG", "getTelecomByCountryList: 0 ${
//        countryCode().find {
//            it.dialCode == extractCode(dial_code)
//        }?.airtimeLimits
//    }")
//    return countryCode().find {
//        it.dialCode == extractCode(dial_code)
//    }?.airtimeLimits
//}
//
//fun getTelecomByCountryListSize(dial_code: String): Boolean {
//    Log.d("TAG", "getTelecomByCountryListSize: 0 ${dial_code}")
//    Log.d("TAG", "getTelecomByCountryListSize: 1 ${extractCode(dial_code)}")
//    val list = countryCode().find {
//        it.dialCode == extractCode(dial_code)
//    }?.airtimeLimits
//    return list?.size!! > 1
//}
//
//fun isValidPhone(phone: String): Boolean {
//    // TODO: find out the minimum phone length per country, 10 for uganda
//    return phone.length < MIN_PHONE_LENGTH || phone.length > MIN_PHONE_LENGTH
//}
//
//fun isFieldNoBlank(field: String): Boolean {
//    return field.isNotBlank()
//}
//
///***
// * user must enter number in this format 0779456733
// * **/
//fun isValidPhoneFormat(
//    phone: String,
//    dial_code: String
//): String {
//    // TODO: check when the values starts with 07.., +254.., 77...254...
//    return "${extractCode(dial_code)}${if (phone[0] == '0') phone.drop(1) else phone}"
//}
//
//fun extractCode(dial_code: String) = dial_code.substringAfterLast(" ")
