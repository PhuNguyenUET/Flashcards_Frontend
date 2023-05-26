package com.example.flashcards.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlipCard(
    flashCard: FlashCard
) {

    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        // When the provided target value is changed, the animation run automatically
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Card(
        Modifier
            .width(300.dp)
            .height(220.dp)
            .graphicsLayer {
                rotationX = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            }
                .border(BorderStroke(2.dp, Color.Gray)),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White
    )
    {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = if (rotated) flashCard.definition else flashCard.word,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationX = rotation
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FlipCardPreview () {
    val card = FlashCard("Word", "Definition")
    FlipCard(card)
}