package com.example.flashcards.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.FlashCard

@Composable
fun PlusButton (
    size: Int,
    onAddClicked: () -> Unit
) {
    IconButton(
        onClick = onAddClicked,
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(size.dp)
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
}

@Composable
fun AddMoreScreen (
    cardList: CardList,
    idx: Int,
    onDoneClicked: () -> Unit,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    var tempList = cardList.words
    var key by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf(cardList.title) }
    var description by remember { mutableStateOf(cardList.description) }
    val focusManager = LocalFocusManager.current
    val onAdd: () -> Unit = {
        val card = FlashCard()
        tempList.add(card)
        key ++
    }
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.yukki_chan),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.edit_list),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.done),
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 18.sp),
                    modifier = Modifier.clickable {
                        cardViewModel.changeList(
                            words = tempList,
                            idx = idx,
                            title = title,
                            description = description
                        )
                        onDoneClicked()
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextField(
                labelId = R.string.title,
                onValueChange = { title = it },
                value = title,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            CommonTextField(
                labelId = R.string.description,
                onValueChange = { description = it },
                value = description,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.words_in_list),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                PlusButton(size = 20, onAddClicked = onAdd)
                Spacer(modifier = Modifier.width(4.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false)
            ) {
                for (card in tempList) {
                    CardBox(card = card, onTermChange = {card.word = it; key --}, onDefChange = { card.definition = it; key ++})
                    Spacer(modifier = Modifier.height(10.dp))
                }
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

@Composable
fun CardBox (
    card: FlashCard,
    onTermChange: (String) -> Unit,
    onDefChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Card (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CommonTextField(
                labelId = R.string.term,
                onValueChange = onTermChange,
                value = card.word,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))
            CommonTextField(
                labelId = R.string.definition,
                onValueChange = onDefChange,
                value = card.definition,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
            )
        }
    }
}

@Composable
fun CommonTextField (
    @StringRes labelId: Int,
    onValueChange: (String) -> Unit,
    value: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = labelId)) },
        modifier = modifier.fillMaxWidth(),
        singleLine = false,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Preview (showBackground = true)
@Composable
fun AddMorePreview () {
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
    AddMoreScreen(cardList = cardList, idx = 1, onDoneClicked = {}, cardViewModel = viewModel())
}