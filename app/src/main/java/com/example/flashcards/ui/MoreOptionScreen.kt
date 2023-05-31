package com.example.flashcards.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun CommonRow (
    onButtonCLicked: () -> Unit,
    icon: ImageVector,
    @StringRes desId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, color = Color.LightGray))
            .clickable(onClick = onButtonCLicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onButtonCLicked) {
            Icon (imageVector = icon, contentDescription = stringResource(id = desId))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(id = textId),
            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        )
    }
}

@Composable
fun MoreOptionScreen (
    onEditClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val uiState by cardViewModel.uiState.collectAsState()
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
                onButtonCLicked = onEditClicked,
                icon = Icons.Filled.Edit,
                desId = R.string.edit,
                textId = R.string.edit_list
            )
            CommonRow(
                onButtonCLicked = onShareClicked,
                icon = Icons.Filled.Share,
                desId = R.string.share,
                textId = R.string.share
            )
            CommonRow(
                onButtonCLicked = {
                    cardViewModel.removeListByIdx(uiState.idx)
                    onDeleteClicked()
                                  },
                icon = Icons.Filled.Delete,
                desId = R.string.delete,
                textId = R.string.delete_list
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
fun MoreOptionPreview () {
    MoreOptionScreen(
        onEditClicked = { /*TODO*/ },
        onShareClicked = { /*TODO*/ },
        onDeleteClicked = { /*TODO*/ },
        onCancelClicked = { /*TODO*/ },
        cardViewModel = viewModel()
    )
}