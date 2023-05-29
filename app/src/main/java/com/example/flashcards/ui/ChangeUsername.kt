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
import com.example.flashcards.ui.components.InfoField

@Composable
fun ChangeUsername (
    onChangeClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    var currentOldUserName by remember { mutableStateOf("") }
    var currentNewUserName by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(true) }
    var checkEmpty by remember { mutableStateOf(true) }
    val interactionSourceButton: MutableInteractionSource = remember { MutableInteractionSource() }
    val isButtonPressed by interactionSourceButton.collectIsPressedAsState()
    if (isButtonPressed) {
        check = currentOldUserName == cardUiState.username
        checkEmpty = currentNewUserName.isNotBlank()
        if (!check && !checkEmpty) {
            checkEmpty = true
        }
    }
    val nextAction: () -> Unit = if (check && checkEmpty) {
        { onChangeClicked()
        cardViewModel.setUsername(username = currentNewUserName) }
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
                text = stringResource(id = R.string.change_username),
                color = Color.Blue,
                style = TextStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, fontSize = 30.sp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            InfoField(
                name = stringResource(id = R.string.old_username) + "  ",
                label = stringResource(id = R.string.old_username),
                value = currentOldUserName,
                onValueChange = { currentOldUserName = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                wrongInfo = !check,
                fontSize = 18
            )

            Spacer(modifier = Modifier.height(20.dp))

            InfoField(
                name = stringResource(id = R.string.new_username),
                label = stringResource(id = R.string.new_username),
                value = currentNewUserName,
                onValueChange = { currentNewUserName = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
                wrongInfo = !checkEmpty,
                fontSize = 18
            )

            Spacer(modifier = Modifier.height(15.dp))
            if (!check) {
                Text(text = stringResource(
                    id = R.string.old_username_not_match),
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            if (!checkEmpty) {
                Text(text = stringResource(
                    id = R.string.new_username_empty),
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

@Preview (showBackground = true)
@Composable
fun ChangeUsernamePreview () {
    ChangeUsername(onChangeClicked = { /*TODO*/ }, cardViewModel = viewModel())
}