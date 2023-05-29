package com.example.flashcards.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.CardShort
import com.example.flashcards.ui.components.FlashCard
import com.example.flashcards.ui.components.FlipCard

@Composable
fun CardDisplayScreen(
    cardList: CardList,
    modifier: Modifier = Modifier,
    onOptionClicked: () -> Unit,
    cardViewModel: CardViewModel
) {
    var idx by remember { mutableStateOf(0) }
    val n = cardList.words.size
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = cardList.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 30.sp
                    ),
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onOptionClicked) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(id = R.string.option))
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { idx -= 1 },
                    enabled = (idx != 0),
                ) {
                    Icon(imageVector = Icons.Filled.ArrowLeft, contentDescription = null)
                }
                FlipCard(flashCard = cardList.words[idx])
                IconButton(
                    onClick = { idx += 1 },
                    enabled = (idx != n - 1),
                ) {
                    Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = null)
                }
            }
            Spacer(Modifier.height(20.dp))
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(id = R.string.words_in_list),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 30.sp
                    ),
                    color = Color.Blue
                )
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                ) {
                    for ((idex, item) in cardList.words.withIndex()) {
                        CardShort(flashCard = item, cardViewModel = cardViewModel, idx = idex)
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardDisplayPreview() {
    val special = FlashCard(word = "test", definition = "Ảo ma canada mafia Argentina Malaysia California Australia Austria Venezuela Romania Lazada Sri Lanka Sakura Haibara Edogawa Conan Naruto Nami Roronoa Zoro Sạnji Kaido Shanks nhảy lambada chachacha Chaien đấm Nobita và làm Shizuka nhòe đi Mascara khi nghe Vascara.")
    val card1 = FlashCard(word = "word1", definition = "definiton1")
    val card2 = FlashCard(word = "word2", definition = "def2")
    val cards = listOf(card1, card2, special, card1, card2, card1, card2, card1, card2, special)
    val cardList = CardList(
        title = "Title",
        description = "test",
        photoId = R.drawable.profile_photo_1,
        words = cards
    )
    CardDisplayScreen(cardList = cardList,
        onOptionClicked = { },
        cardViewModel = viewModel()
    )
}