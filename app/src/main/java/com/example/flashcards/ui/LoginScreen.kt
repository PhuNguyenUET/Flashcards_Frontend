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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.components.InfoField
import com.example.flashcards.ui.components.PassField

@Composable
fun LoginScreen (
    onSiteChanged: () -> Unit,
    onLoginClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel = viewModel()
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    var currentUsername by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val interactionSourceButton: MutableInteractionSource = remember { MutableInteractionSource() }
    val isButtonPressed by interactionSourceButton.collectIsPressedAsState()
    val nextAction: () -> Unit = if (cardUiState.correctLogIn) {
        onLoginClicked
    } else {
        {}
    }
    if (isButtonPressed) {
        cardViewModel.checkLogin(currentUsername, currentPassword)
    }
    Box (
        modifier = modifier.fillMaxSize(),
    ) {
        Image(painter = painterResource(id = R.drawable.shinobu_sword),
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
                    color = Color.Gray.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        color = if (isPressed) Color.Red else Color.Black,
                        style = TextStyle(fontSize = 30.sp),
                        modifier = Modifier.clickable(
                            onClick = onSiteChanged,
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        )
                    )
                }
                Spacer (modifier.width(24.dp))
                Surface(
                    color = Color.White.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in),
                        color = Color.Red,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
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
                        nameId = R.string.username,
                        labelId = R.string.username,
                        value = currentUsername,
                        onValueChange = { currentUsername = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        wrongInfo = !cardUiState.correctLogIn,
                    )

                    PassField(
                        name = stringResource(id = R.string.password) + ' ',
                        label = stringResource(id = R.string.password),
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        onIconClicked = {passwordVisible = !passwordVisible},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        wrongInfo = !cardUiState.correctLogIn,
                        passwordVisible = passwordVisible
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.question),
                            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(id = R.string.register),
                            color = Color.Blue,
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.clickable(onClick = onSiteChanged)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    if (!cardUiState.correctLogIn) {
                        Text(text = stringResource(
                            id = R.string.wrong_info_noti),
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
                        Text(text = stringResource(id = R.string.log_in))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenDefaultPreview () {
    LoginScreen(onSiteChanged = { /*TODO*/ }, onLoginClicked = { /*TODO*/ })
}