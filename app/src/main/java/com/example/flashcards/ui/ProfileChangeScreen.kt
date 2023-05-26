package com.example.flashcards.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
fun ProfileChangeScreen (
    onCancelClicked: () -> Unit,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel = viewModel()
) {
    val photoIdList = listOf(
        R.drawable.profile_photo_1,
        R.drawable.profile_photo_2,
        R.drawable.profile_photo_3,
        R.drawable.profile_photo_4,
        R.drawable.profile_photo_5,
        R.drawable.profile_photo_6,
        R.drawable.profile_photo_7,
        R.drawable.profile_photo_8,
        R.drawable.profile_photo_9,
        R.drawable.profile_photo_10
    )
    val cardUiState by cardViewModel.uiState.collectAsState()
    var idx by remember { mutableStateOf(-1) }
    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Image (
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Text (
                    text = stringResource(id = R.string.cancel),
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp),
                    modifier = Modifier.clickable(onClick = onCancelClicked)
                )
                Spacer(Modifier.weight(1f))
                Text (
                    text = stringResource(id = R.string.save),
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp),
                    modifier = Modifier.clickable(onClick = onSaveClicked)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Spacer (modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(id = if (idx == -1) {
                    cardUiState.profilePicId
                } else {
                    photoIdList[idx]
                }),
                contentDescription = stringResource(id = R.string.profile_picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text (
                text = stringResource(id = R.string.current_picture),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 20.sp)
            )
            Spacer(Modifier.height(15.dp))
            Text (
                text = stringResource(id = R.string.pick_new_one),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(1f, fill = false)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CommonPicture(onPhotoClicked = { idx = 0 }, photoId = photoIdList[0])
                    CommonPicture(onPhotoClicked = { idx = 1 }, photoId = photoIdList[1])
                    CommonPicture(onPhotoClicked = { idx = 2 }, photoId = photoIdList[2])
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CommonPicture(onPhotoClicked = { idx = 3 }, photoId = photoIdList[3])
                    CommonPicture(onPhotoClicked = { idx = 4 }, photoId = photoIdList[4])
                    CommonPicture(onPhotoClicked = { idx = 5 }, photoId = photoIdList[5])
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CommonPicture(onPhotoClicked = { idx = 6 }, photoId = photoIdList[6])
                    CommonPicture(onPhotoClicked = { idx = 7 }, photoId = photoIdList[7])
                    CommonPicture(onPhotoClicked = { idx = 8 }, photoId = photoIdList[8])
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CommonPicture(onPhotoClicked = { idx = 9 }, photoId = photoIdList[9])
                }
            }
        }
    }
}

@Composable
fun CommonPicture (
    onPhotoClicked: () -> Unit,
    @DrawableRes photoId: Int
) {
    Image(
        painter = painterResource(id = photoId),
        contentDescription = stringResource(id = R.string.profile_picture),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(90.dp)
            .height(90.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .clickable(onClick = onPhotoClicked)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileChangePreview () {
    ProfileChangeScreen(onCancelClicked = { /*TODO*/}, onSaveClicked = {})
}