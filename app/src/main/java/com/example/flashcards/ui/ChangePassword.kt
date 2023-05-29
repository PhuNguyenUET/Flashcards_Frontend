package com.example.flashcards.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.components.PassField

@Composable
fun ChangePassword (
    onChangeClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    var currentOldPassword by remember { mutableStateOf("") }
    var currentNewPassword by remember { mutableStateOf("") }
    var currentConfirmPassword by remember { mutableStateOf("") }
    var oldPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var checkMatch by remember { mutableStateOf(true) }
    var check by remember { mutableStateOf(true) }
    var checkEmpty by remember { mutableStateOf(true) }
    val interactionSourceButton: MutableInteractionSource = remember { MutableInteractionSource() }
    val isButtonPressed by interactionSourceButton.collectIsPressedAsState()
    if (isButtonPressed) {
        check = currentOldPassword == cardUiState.password
        checkEmpty = currentNewPassword.isNotBlank()
        checkMatch = currentNewPassword == currentConfirmPassword
        if (!check) {
            checkEmpty = true
            checkMatch = true
        } else if (!checkEmpty) {
            checkMatch = true
        }
    }
    val nextAction: () -> Unit = if (check && checkEmpty) {
        { onChangeClicked()
        cardViewModel.setPassword(password = currentNewPassword) }
    } else {
        {}
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.change_password),
                color = Color.Blue,
                style = TextStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, fontSize = 30.sp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            PassField(
                name = stringResource(id = R.string.old_password) + "        ",
                label = stringResource(id = R.string.old_password),
                value = currentOldPassword,
                onValueChange = { currentOldPassword = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                wrongInfo = !check,
                fontSize = 18,
                onIconClicked = {oldPasswordVisible = !oldPasswordVisible},
                passwordVisible = oldPasswordVisible
            )

            Spacer(modifier = Modifier.height(20.dp))

            PassField(
                name = stringResource(id = R.string.new_password) + "      ",
                label = stringResource(id = R.string.new_password),
                value = currentNewPassword,
                onValueChange = { currentNewPassword = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                wrongInfo = !checkEmpty,
                fontSize = 18,
                onIconClicked = {newPasswordVisible = !newPasswordVisible},
                passwordVisible = newPasswordVisible
            )

            Spacer(modifier = Modifier.height(20.dp))

            PassField(
                name = stringResource(id = R.string.confirm_password),
                label = stringResource(id = R.string.confirm_password),
                value = currentConfirmPassword,
                onValueChange = { currentConfirmPassword = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
                wrongInfo = !checkMatch,
                fontSize = 18,
                onIconClicked = { },
                passwordVisible = false
            )

            Spacer(modifier = Modifier.height(15.dp))
            if (!check) {
                Text(text = stringResource(
                    id = R.string.password_not_match),
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            if (!checkEmpty) {
                Text(text = stringResource(
                    id = R.string.pass_not_empty),
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            if (!checkMatch) {
                Text(text = stringResource(
                    id = R.string.confirm_not_match),
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            Button(
                border = BorderStroke(width = 1.dp, color = Color.Blue),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 12.dp,
                    pressedElevation = 4.dp
                ),
                onClick = nextAction,
                shape = RoundedCornerShape(16.dp),
                interactionSource = interactionSourceButton
            ) {
                Text(text = stringResource(id = R.string.change))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview () {
    ChangePassword(onChangeClicked = { /*TODO*/ }, cardViewModel = viewModel())
}