package com.maku.featureairtime.airtime.self

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
import androidx.compose.ui.semantics.semantics
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
import com.maku.featureairtime.data.uiState.ForSelfAirtimeUiState
@Composable
fun BuyForSelfScreen(
    viewModel: SelfViewModel = hiltViewModel(),
) {
    SelfScreen(
        viewModel.uiState.value,
        viewModel::onBuyAirtimeClick,
        viewModel::onMyAmountChange,
    )
}
@Composable
fun SelfScreen(
    forSelfAirtimeUiState: com.maku.featureairtime.data.uiState.ForSelfAirtimeUiState,
    onBuyAirtimeClick: () -> Unit,
    onMyAmountChange: (
        String
    ) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (title, phoneInput, btn) = createRefs()

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.matchParent
            },
            text =  stringResource(id = R.string.enter_amount) ,
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Start,
            maxLines = 2
        )

        OutlinedTextField(
            value = forSelfAirtimeUiState.amount,
            onValueChange = {
                onMyAmountChange(it)
            },
            singleLine = true,
            label = {
                Text(
                    if (forSelfAirtimeUiState.error)
                        stringResource(id = R.string.amount_error)
                    else {
                        stringResource(id = R.string.amount)
                    }
                )
            },
            isError = forSelfAirtimeUiState.error,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

        Button(
            onClick = {
                onBuyAirtimeClick()
            },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .constrainAs(btn) {
                    top.linkTo(phoneInput.bottom, 16.dp)
                    end.linkTo(phoneInput.end)
                    width = Dimension.wrapContent
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                stringResource(
                    id = R.string.buy_airtime,
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelfScreenPreview() {
    SelfScreen(
        forSelfAirtimeUiState = ForSelfAirtimeUiState(),
        onBuyAirtimeClick = {},
        onMyAmountChange = {},
    )
}
