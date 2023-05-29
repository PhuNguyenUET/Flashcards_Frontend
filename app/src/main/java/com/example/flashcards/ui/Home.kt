package com.example.flashcards.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.components.FlashCard
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.DiscoveryListShort
import com.example.flashcards.ui.components.ListShort

@Composable
fun Home (
    onCardClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onShareClicked: () -> Unit,
    cardLists: List<CardList>,
    discoveryLists: List<CardList>,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
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
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(id = R.string.achievement),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_achievement),
                    color = Color.Blue,
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.recent),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            val list = cardLists.takeLast(3)
            for ((idx, item) in list.withIndex()) {
                ListShort(
                    name = cardUiState.username,
                    photoId = item.photoId,
                    cardList = item,
                    idx = cardLists.size - list.size + idx,
                    onCardClicked = onCardClicked,
                    onEditClicked = onEditClicked,
                    onShareClicked = onShareClicked,
                    cardViewModel = cardViewModel
                )
            }
            Text(
                text = stringResource(id = R.string.discovery),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            val discover = discoveryLists.takeLast(3)
            for (item in discover) {
                DiscoveryListShort(
                    name = cardUiState.username,
                    photoId = item.photoId,
                    listName = item.title,
                    onCardClicked = onCardClicked,
                    onAddClicked = {
                        cardViewModel.addList(item)
                                   },
                    onShareClicked = onShareClicked
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview () {
    val flashCards: List<FlashCard> = listOf()
    val cardList = CardList(title = "Test", description = "Test", photoId = R.drawable.profile_photo_1, words = flashCards)
    val cardLists: List<CardList> = listOf(cardList, cardList, cardList)
    Home(
        onCardClicked = { /*TODO*/ },
        onEditClicked = { /*TODO*/ },
        onShareClicked = { /*TODO*/ },
        cardLists = cardLists,
        discoveryLists = cardLists,
        cardViewModel = viewModel()
    )
}