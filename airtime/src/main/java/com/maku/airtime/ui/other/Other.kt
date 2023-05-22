package com.maku.airtime.ui.other

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maku.airtime.data.uiState.ForAnotherAirtimeUiState
import com.maku.core.ui.R

@Composable
fun BuyForOtherScreen(
    viewModel: OtherViewModel = hiltViewModel()
) {
    OtherScreen(
        viewModel.forAnotherUiState.value,
        viewModel::onBuyAirtimeForAnotherClick,
        viewModel::onAnotherDialCodeChange,
        viewModel::onAnotherAirtimeLimitChange,
        viewModel::onAnotherPhoneChange,
        viewModel::onAnotherAmountChange,
        viewModel.userPhoneHasLocalError,
        viewModel.userAmountHasLocalError
    )
}

@Composable
fun OtherScreen(
    forAnotherUiState: ForAnotherAirtimeUiState,
    onBuyAirtimeForAnotherClick: (Boolean, Boolean) -> Unit,
    onAnotherDialCodeChange: (String) -> Unit,
    onAnotherAirtimeLimitChange: (String) -> Unit,
    onAnotherPhoneChange: (String) -> Unit,
    onAnotherAmountChange: (String) -> Unit,
    userPhoneHasLocalError: Pair<Boolean, String>,
    userAmountHasLocalError: Pair<Boolean, String>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val limit: String = forAnotherUiState.airtimeLimit
        .substring(
            forAnotherUiState.airtimeLimit.indexOf("(") + 1,
            forAnotherUiState.airtimeLimit.indexOf(")")
        )
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(id = R.string.enter_amount_phone),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Start,
                maxLines = 2
            )
        }

        item {
            CountryDropDownMenu(
                forAnotherUiState,
                onAnotherDialCodeChange
            )
        }

        if (forAnotherUiState.airtimeLimitList.size > 1) {
            item {
                TelecomDropDownMenu(
                    forAnotherUiState,
                    onAnotherAirtimeLimitChange
                )
            }
        }

        item {
            OutlinedTextField(
                value = forAnotherUiState.phone,
                onValueChange = {
                    onAnotherPhoneChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                label = {
                    Text(
                        stringResource(id = R.string.phone_eg)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                supportingText = {
                    if (userPhoneHasLocalError.first) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = userPhoneHasLocalError.second,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }

        item {
            OutlinedTextField(
                value = forAnotherUiState.amount ?: "0",
                onValueChange = {
                    onAnotherAmountChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                label = {
                    Text("Amount ($limit)")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                supportingText = {
                    if (userAmountHasLocalError.first) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = userAmountHasLocalError.second,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }

        item {
            Button(
                onClick = {
                    onBuyAirtimeForAnotherClick(
                        userAmountHasLocalError.first,
                        userPhoneHasLocalError.first
                    )
                },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    stringResource(
                        id = R.string.buy_airtime
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun CountryDropDownMenu(
    forAnotherUiState: ForAnotherAirtimeUiState,
    onAnotherDialCodeChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // TODO: set the default country code after user authenticates or onboards
        OutlinedTextField(
            value = forAnotherUiState.dial_code,
            onValueChange = { onAnotherDialCodeChange(it) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            label = {
                Text(text = stringResource(id = R.string.select_country))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            maxLines = 1
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            forAnotherUiState.airtimeCountryList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.name + " " + item.dialCode
                        )
                    },
                    onClick = {
                        selectedText = item.emoji + " " + item.dialCode
                        onAnotherDialCodeChange(item.emoji + " " + item.dialCode)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TelecomDropDownMenu(
    forAnotherUiState: ForAnotherAirtimeUiState,
    onAnotherAirtimeLimitChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedAirtimeLimitText by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = forAnotherUiState.airtimeLimit,
            onValueChange = {
                onAnotherAirtimeLimitChange(it)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            label = {
                Text(
                    text = stringResource(id = R.string.select_telecom)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            maxLines = 1
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            forAnotherUiState.airtimeLimitList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.name + " (" + item.lower + " - " + item.upper + ")"
                        )
                    },
                    onClick = {
                        selectedAirtimeLimitText =
                            item.name + " (" + item.lower + " - " + item.upper + ")"
                        onAnotherAirtimeLimitChange(
                            item.name + " (" + item.lower + " - " + item.upper + ")"
                        )
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview(
    name = "phone",
    uiMode = 32,
    showSystemUi = true,
    showBackground = false,
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
fun BuyForOtherScreenPreview() {
    OtherScreen(
        forAnotherUiState = ForAnotherAirtimeUiState(),
        onBuyAirtimeForAnotherClick = { _, _ ->
            {

            }
        },
        onAnotherDialCodeChange = {},
        onAnotherAirtimeLimitChange = {},
        onAnotherPhoneChange = {},
        onAnotherAmountChange = {},
        userPhoneHasLocalError = Pair(true, "An error occurred"),
        userAmountHasLocalError = Pair(true, "An error occurred")
    )
}
