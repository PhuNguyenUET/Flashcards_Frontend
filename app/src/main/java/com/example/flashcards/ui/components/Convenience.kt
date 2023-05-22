package com.example.flashcards.ui.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcards.R

@Composable
fun InfoField (
    @StringRes nameId: Int,
    @StringRes labelId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    wrongInfo: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text (text = stringResource(id = nameId), style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.width(12.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {Text(stringResource(id = labelId))},
            isError = wrongInfo,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
    }
}

@Composable
fun PassField (
    name: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onIconClicked: () -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    wrongInfo: Boolean,
    passwordVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text (text = name, style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.width(12.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {Text(name)},
            isError = wrongInfo,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = onIconClicked) {
                    Icon (imageVector = image, null)
                }
            }
        )
    }
}