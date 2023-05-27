package com.example.flashcards.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoField (
    name: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    wrongInfo: Boolean,
    modifier: Modifier = Modifier,
    fontSize: Int = 24
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (text = name, style = TextStyle(fontSize = fontSize.sp))
        Spacer(modifier = Modifier.width(12.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {Text(label)},
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
    modifier: Modifier = Modifier,
    fontSize: Int = 24
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (text = name, style = TextStyle(fontSize = fontSize.sp))
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