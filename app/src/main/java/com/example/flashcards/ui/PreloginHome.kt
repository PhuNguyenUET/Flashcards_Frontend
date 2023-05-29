package com.example.flashcards.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcards.R

@Composable
private fun CommonButton(
    @StringRes resourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button (
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = resourceId), style = TextStyle(textAlign = TextAlign.Center))
    }
}

@Composable
fun PreLoginHome (
    onLoginButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.shinobu_sword),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.width(200.dp))
                CommonButton(
                    resourceId = R.string.register,
                    onClick = onRegisterButtonClicked,
                    modifier = Modifier.width(80.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                CommonButton(
                    resourceId = R.string.log_in,
                    onClick = onLoginButtonClicked,
                    modifier = Modifier.width(70.dp)
                )
            }
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome),
                        fontSize = 40.sp,
                        style = TextStyle(textAlign = TextAlign.Center)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = stringResource(id = R.string.des),
                        fontSize = 24.sp,
                        style = TextStyle(textAlign = TextAlign.Justify)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    CommonButton(resourceId = R.string.get_started, onClick = onLoginButtonClicked, modifier = Modifier.width(300.dp))
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun DefaultPreview() {
    PreLoginHome(
        onLoginButtonClicked = { /*TODO*/ },
        onRegisterButtonClicked = { /*TODO*/ }
    )
}