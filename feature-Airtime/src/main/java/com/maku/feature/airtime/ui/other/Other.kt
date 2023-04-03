package com.maku.feature.airtime.ui.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.maku.core.ui.R
import com.maku.feature.airtime.data.uiState.ForAnotherAirtimeUiState

@Composable
fun BuyForOtherScreen(
    viewModel: OtherViewModel = hiltViewModel()
) {
    OtherScreen(
        viewModel.forAnotherUiState.value,
        viewModel::onBuyAirtimeForAnotherClick,
        viewModel::onAnotherAmountChange,
        viewModel::onAnotherPhoneChange
    )
}

@Composable
fun OtherScreen(
    forAnotherUiState: ForAnotherAirtimeUiState,
    onBuyAirtimeForAnotherClick: () -> Unit,
    onAnotherAmountChange: (String) -> Unit,
    onAnotherPhoneChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    // TODO: remove repetition // optimize this shit ...
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (title, phoneInput, amountInput, btn) = createRefs()

        Text(
            text = stringResource(id = R.string.enter_amount_phone),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Start,
            maxLines = 2
        )

        OutlinedTextField(
            value = forAnotherUiState.phone,
            onValueChange = {
                onAnotherPhoneChange(it)
            },
            singleLine = true,
            label = {
                Text(
                    if (forAnotherUiState.error) {
                        stringResource(id = R.string.phone_error)
                    } else {
                        stringResource(id = R.string.phone_eg)
                    }
                )
            },
            isError = forAnotherUiState.error,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .constrainAs(phoneInput) {
                    top.linkTo(title.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.textFieldColors(
                // to set colors, look into the colors.kt file
            )
        )

        OutlinedTextField(
            value = forAnotherUiState.amount,
            onValueChange = {
                onAnotherAmountChange(it)
            },
            singleLine = true,
            label = {
                Text(
                    if (forAnotherUiState.error) {
                        stringResource(id = com.maku.core.ui.R.string.amount_error)
                    } else {
                        stringResource(
                            id = R.string.amount
                        )
                    }
                )
            },
            isError = forAnotherUiState.error,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .constrainAs(amountInput) {
                    top.linkTo(phoneInput.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.textFieldColors(
                // to set colors, look into the colors.kt file
            )
        )

        Button(
            onClick = {
                onBuyAirtimeForAnotherClick()
            },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .constrainAs(btn) {
                    top.linkTo(amountInput.bottom, 16.dp)
                    end.linkTo(amountInput.end)
                    width = Dimension.wrapContent
                },
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
        onBuyAirtimeForAnotherClick = {},
        onAnotherAmountChange = {},
        onAnotherPhoneChange = {}
    )
}
