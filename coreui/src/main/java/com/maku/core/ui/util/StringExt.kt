package com.maku.core.ui.util

private const val MIN_AMOUNT_LENGTH = 2 // TODO: research AT's limits, and localise
private const val MIN_PHONE_LENGTH = 10 // TODO: find out the minimum phone length per country

fun String.isValidAmount(): Boolean {
    return this.isNotBlank() && this.length >= MIN_AMOUNT_LENGTH
}

fun String.isValidPhone(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PHONE_LENGTH
}
