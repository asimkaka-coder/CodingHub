package com.example.codinghub.auth.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier,
    label: String,
    stateValue: MutableState<TextFieldValue>,
    isPasswordField:Boolean =false
) {
    OutlinedTextField(
        value = stateValue.value, onValueChange = { stateValue.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp),
        visualTransformation = if (!isPasswordField) {
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}