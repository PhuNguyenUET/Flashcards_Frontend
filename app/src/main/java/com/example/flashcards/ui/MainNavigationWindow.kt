package com.example.flashcards.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.flashcards.R

@Composable
fun MainNavigationScreen (
    onYourListClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonRow(
                onButtonCLicked = onYourListClicked,
                icon = Icons.Filled.Book,
                desId = R.string.your_lists,
                textId = R.string.your_lists
            )
            CommonRow(
                onButtonCLicked = onProfileClicked,
                icon = Icons.Filled.Person,
                desId = R.string.profile,
                textId = R.string.profile
            )
            CommonRow(
                onButtonCLicked = onSettingsClicked,
                icon = Icons.Filled.Settings,
                desId = R.string.settings,
                textId = R.string.settings
            )
            CommonRow(
                onButtonCLicked = onLogoutClicked,
                icon = Icons.Filled.Logout,
                desId = R.string.log_out,
                textId = R.string.log_out
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, color = Color.LightGray))
                    .clickable(onClick = onCancelClicked),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp, fontStyle = FontStyle.Italic)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigationPreview () {
    MainNavigationScreen(
        onYourListClicked = { /*TODO*/ },
        onProfileClicked = { /*TODO*/ },
        onSettingsClicked = { /*TODO*/ },
        onLogoutClicked = { /*TODO*/ },
        onCancelClicked = { /*TODO*/ },
    )
}