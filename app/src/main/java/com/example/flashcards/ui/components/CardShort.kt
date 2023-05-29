package com.example.flashcards.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcards.R
import com.example.flashcards.ui.CardViewModel

@Composable
fun CardShort (
    flashCard: FlashCard,
    idx: Int,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel
) {
    val cardUiState by cardViewModel.uiState.collectAsState()
    var isStarred by remember { mutableStateOf(flashCard.isStarred) }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = flashCard.word,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(weight = 1f, fill = false),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = flashCard.definition,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Justify
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        val starImage: ImageVector
        val msg: String
        val tint: Color
        if (isStarred) {
            starImage = Icons.Filled.Star
            msg = stringResource(id = R.string.star)
            tint = Color.Yellow
        } else {
            starImage = Icons.Outlined.StarOutline
            msg = stringResource(id = R.string.star)
            tint = Color.Black
        }
        IconButton(onClick = {
            flashCard.isStarred = !flashCard.isStarred
            isStarred = !isStarred
        }) {
            Icon(imageVector = starImage, contentDescription = msg, tint = tint)
        }
        IconButton(onClick = {
            cardUiState.lists[cardUiState.idx].removeAtIdx(idx)
        }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(id = R.string.edit))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardShortPreview () {
    val card = FlashCard(word = "test", definition = "Ảo ma canada mafia Argentina Malaysia California Australia Austria Venezuela Romania Lazada Sri Lanka Sakura Haibara Edogawa Conan Naruto Nami Roronoa Zoro Sạnji Kaido Shanks nhảy lambada chachacha Chaien đấm Nobita và làm Shizuka nhòe đi Mascara khi nghe Vascara.")
    CardShort(
        flashCard = card,
        cardViewModel = viewModel(),
        idx = 0
    )
}
