package com.example.flashcards.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.flashcards.ui.components.FlashCard
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.ListShort

@Composable
fun YourListScreen (
    onCardClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onAddMoreClicked: () -> Unit,
    cardLists: List<CardList>,
    cardViewModel: CardViewModel,
    modifier: Modifier = Modifier
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    val onAdd = {
        onAddMoreClicked()
        cardViewModel.changeNew()
    }
    Box (
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.your_lists),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false),
                verticalArrangement = Arrangement.Center
            ) {
                for ((idx, item) in cardLists.withIndex()) {
                    ListShort(
                        name = cardUiState.username,
                        photoId = item.photoId,
                        cardList = item,
                        idx = idx,
                        onCardClicked = onCardClicked,
                        onEditClicked = onEditClicked,
                        onShareClicked = onShareClicked,
                        cardViewModel = cardViewModel
                    )
                }
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onAdd) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(id = R.string.add_more),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourListPreview () {
    val flashCards: List<FlashCard> = listOf()
    val cardList = CardList(title = "Test", description = "Test", photoId = R.drawable.profile_photo_1, words = flashCards)
    val cardLists: List<CardList> = listOf(cardList, cardList, cardList, cardList, cardList, cardList, cardList, cardList, cardList, cardList, cardList, cardList)
    YourListScreen(
        onCardClicked = { /*TODO*/ },
        onEditClicked = { /*TODO*/ },
        onShareClicked = { /*TODO*/ },
        onAddMoreClicked = { },
        cardLists = cardLists,
        cardViewModel = viewModel()
    )
}