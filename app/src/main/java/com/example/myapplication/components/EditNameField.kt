package com.example.myapplication.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction

@Composable
fun EditNameField(
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    modifier: Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { !it.isDigit() }
            onValueChange(filteredValue)
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
                modifier = modifier.clickable {
                    onDone()
                }
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            onDone()
            focusRequester.requestFocus()
        }),
        modifier = modifier.focusRequester(focusRequester)
    )
}