package com.example.flashcards.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.flashcards.ui.CardViewModel

@Composable
fun ListShort (
    name: String,
    cardList: CardList,
    idx: Int,
    @DrawableRes photoId: Int,
    onCardClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onShareClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer (Modifier.width(32.dp))
        Row(
            modifier = Modifier.clickable(
                onClick = {
                    cardViewModel.updateIdx(idx = idx)
                    onCardClicked()
                }
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = photoId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(percent = 50))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = cardList.title,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Text(
                    text = stringResource(id = R.string.created_by, name),
                    color = Color.Gray,
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 6.sp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.End
        ) {
            val starImage: ImageVector
            val msg: String
            val tint: Color
            if (cardList.isStarred) {
                starImage = Icons.Filled.Star
                msg = stringResource(id = R.string.star)
                tint = Color.Yellow
            } else {
                starImage = Icons.Outlined.StarOutline
                msg = stringResource(id = R.string.star)
                tint = Color.Black
            }
            IconButton(
                onClick = {
                    cardViewModel.changeStar(idx)
                }
            ) {
                Icon(imageVector = starImage, contentDescription = msg, tint = tint)
            }
            IconButton(onClick = {
                cardViewModel.updateIdx(idx = idx)
                onEditClicked()
            }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = stringResource(id = R.string.edit))
            }
            IconButton(
                onClick = {
                    cardViewModel.removeListByIdx(idx)
                }
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(id = R.string.delete))
            }
            IconButton(onClick = onShareClicked) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = stringResource(id = R.string.share))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview () {
    ListShort(
        name = "skylawson",
        photoId = R.drawable.profile_photo_1,
        cardList = CardList(),
        onCardClicked = { /*TODO*/ },
        onEditClicked = { /*TODO*/ },
        onShareClicked = { /*TODO*/ },
        idx = 0,
        cardViewModel = viewModel())
}