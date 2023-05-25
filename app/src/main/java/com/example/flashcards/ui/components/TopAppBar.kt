package com.example.flashcards.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
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
import com.example.flashcards.R

@Composable
fun topAppBar (
    @DrawableRes profileId: Int,
    onMenuClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = onMenuClicked,
            modifier = Modifier.border(BorderStroke(1.dp, color = Color.LightGray))
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(id = R.string.menu)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onAddClicked,
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(50.dp)
                )
                .background(color = Color.Blue, shape = CircleShape)
                .clip(CircleShape)
        )  {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = onAddClicked,
            modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(50.dp))
        )  {
            Icon(imageVector = Icons.Filled.Notifications, contentDescription = stringResource(id = R.string.add))
        }
        Image(
            painter = painterResource(id = profileId),
            contentDescription = stringResource(id = R.string.expand),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clickable(onClick = onProfileClicked)
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(50.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun topBarPreview () {
    topAppBar(
        onMenuClicked = { /*TODO*/ },
        onAddClicked = { /*TODO*/ },
        onProfileClicked = { /*TODO*/ },
        profileId = R.drawable.profile_photo_1
    )
}