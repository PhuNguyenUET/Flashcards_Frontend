package com.example.flashcards.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.ui.components.InfoField
import com.example.flashcards.ui.components.PassField

@Composable
fun RegisterScreen (
    onSiteChanged: () -> Unit,
    onSignupClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val focusManager = LocalFocusManager.current
    var currentUsername by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var currentConfirm by remember { mutableStateOf("") }
    var confirmVisible by remember { mutableStateOf(false) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val interactionSourceButton: MutableInteractionSource = remember { MutableInteractionSource() }
    val isButtonPressed by interactionSourceButton.collectIsPressedAsState()
    var check by remember { mutableStateOf(true) }
    if (isButtonPressed) {
        check = (currentConfirm == currentPassword)
    }
    var usernameCheck by remember { mutableStateOf(true) }
    val nextAction: () -> Unit = if (check && usernameCheck) {
        onSignupClicked
    } else {
        {}
    }
    Box (
        modifier = modifier.fillMaxSize(),
    ) {
        Image(painter = painterResource(id = com.example.flashcards.R.drawable.shinobu_sword),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(160.dp))
            Row (
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Surface(
                    color = Color.White.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(id = com.example.flashcards.R.string.register),
                        color = Color.Red,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    )
                }
                Spacer (modifier.width(24.dp))
                Surface(
                    color = Color.Gray.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(id = com.example.flashcards.R.string.log_in),
                        color = if (isPressed) Color.Red else Color.Black,
                        style = TextStyle(fontSize = 30.sp),
                        modifier = Modifier.clickable(
                            onClick = onSiteChanged,
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        )
                    )
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.5f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InfoField(
                        name = stringResource(id = com.example.flashcards.R.string.username),
                        label = stringResource(id = com.example.flashcards.R.string.username),
                        value = currentUsername,
                        onValueChange = { currentUsername = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                usernameCheck = cardViewModel.checkUsernameUnique(currentUsername)
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        wrongInfo = !usernameCheck,
                    )

                    PassField(
                        name = stringResource(id = com.example.flashcards.R.string.password) + ' ',
                        label = stringResource(id = com.example.flashcards.R.string.password),
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        onIconClicked = {passwordVisible = !passwordVisible},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        wrongInfo = !check,
                        passwordVisible = passwordVisible
                    )

                    PassField(
                        name = stringResource(id = com.example.flashcards.R.string.confirm) + "    ",
                        label = stringResource(id = com.example.flashcards.R.string.confirm),
                        value = currentConfirm,
                        onValueChange = { currentConfirm = it },
                        onIconClicked = {confirmVisible = !confirmVisible},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        wrongInfo = !check,
                        passwordVisible = confirmVisible
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    if (!usernameCheck) {
                        Text(text = stringResource(
                            id = com.example.flashcards.R.string.username_exist),
                            color = Color.Red,
                            style = TextStyle(fontSize = 20.sp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    if (!check) {
                        Text(text = stringResource(
                            id = com.example.flashcards.R.string.do_not_match_noti),
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
                        Text(text = stringResource(id = com.example.flashcards.R.string.register))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenDefaultPreview () {
    RegisterScreen(onSiteChanged = { /*TODO*/ }, onSignupClicked = { /*TODO*/ }, cardViewModel = viewModel())
}