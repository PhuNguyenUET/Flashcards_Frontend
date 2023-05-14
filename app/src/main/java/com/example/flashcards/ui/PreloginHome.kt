package com.example.flashcards.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcards.R

@Composable
private fun CommonButton(
    @StringRes resourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button (
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(text = stringResource(id = resourceId))
    }
}

@Composable
fun PreLoginHome (
    onLoginButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.shinobu_sword),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (
                ) {
            Row () {
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(id = R.string.des))
                Spacer(modifier = Modifier.height(8.dp))
                CommonButton(resourceId = R.string.get_started, onClick = onLoginButtonClicked)
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