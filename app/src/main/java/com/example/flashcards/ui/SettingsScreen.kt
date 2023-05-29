package com.example.flashcards.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R

@Composable
fun SettingsScreen (
    onUsernameChangeClicked: () -> Unit,
    onPasswordChangeClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    val n = cardUiState.username.length
    var shown = ""
    shown += cardUiState.username[0]
    shown += cardUiState.username[1]
    for (i in 1..(n - 2)) {
        shown += '*'
    }
    var passShown = ""
    for (i in 1..(n)) {
        passShown += '*'
    }
    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(50.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.theme),
                        color = Color.Blue,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 34.sp
                        )
                    )
                }
                Row (modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(40.dp))
                    IconButton(
                        onClick = { cardViewModel.changeTheme() },
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .border(BorderStroke(width = 1.dp, color = Color.LightGray))
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(imageVector = Icons.Filled.LightMode, contentDescription = stringResource(id = R.string.light))
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = stringResource(id = R.string.light), style = TextStyle(fontSize = 17.sp))
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { cardViewModel.changeTheme() },
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .border(BorderStroke(width = 1.dp, color = Color.LightGray))
                            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(imageVector = Icons.Filled.DarkMode, contentDescription = stringResource(id = R.string.dark), tint = Color.White)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = stringResource(id = R.string.dark), color = Color.White, style = TextStyle(fontSize = 17.sp))
                        }
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.login_info),
                        color = Color.Blue,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 34.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(width = 1.dp, color = Color.Gray))
                ) {
                    Text(
                        text = stringResource(id = R.string.username),
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = shown,
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ChangeButton(onClick = onUsernameChangeClicked)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(width = 1.dp, color = Color.Gray))
                ) {
                    Text(
                        text = stringResource(id = R.string.password),
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = passShown,
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ChangeButton(onClick = onPasswordChangeClicked)
                }
            }
        }
    }
}

@Composable
fun ChangeButton (
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.change),
        color = Color.Blue,
        style = TextStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 22.sp),
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview (showBackground = true)
@Composable
fun SettingsPreview () {
    SettingsScreen(onUsernameChangeClicked = { /*TODO*/ }, onPasswordChangeClicked = { /*TODO*/ }, cardViewModel = viewModel())
}