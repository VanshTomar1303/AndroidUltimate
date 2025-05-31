package com.vansh.noteapp.screens.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.vansh.noteapp.events.UserEvent
import com.vansh.noteapp.model.UserState
import com.vansh.noteapp.ui.theme.colorLightBlack
import com.vansh.noteapp.ui.theme.colorWhite

@Composable
fun MyOutlinedTextfield(
    modifier: Modifier = Modifier,
    state: String,
    icon: ImageVector,
    name: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = state,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = name,
                tint = Color.White
            )
        },
        label = {
            Text(
                text = name
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorWhite,
            unfocusedTextColor = colorWhite,
            focusedContainerColor = colorLightBlack,
            unfocusedContainerColor = colorLightBlack,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = keyboardOptions
    )
}