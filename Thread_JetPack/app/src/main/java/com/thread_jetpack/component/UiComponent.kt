package com.thread_jetpack.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object UiComponent {

    @Composable
    fun OutLineTextFieldBox(
        value: String,
        onChangeValue: (String) -> Unit,
        label: String,
        keyboardType: KeyboardType = KeyboardType.Text,
        isPassword: Boolean = false,
        modifier: Modifier = Modifier,
        singleLine: Boolean = true
    ) {
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = value,
            onValueChange = onChangeValue,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(6.dp))
    }


    @Composable
    fun ElevatedButtonUi(
        label: String,
        onclick: () -> Unit,
        isEnable: Boolean = true,
        fontSize: TextUnit = 20.sp,
        fontWeight: FontWeight = FontWeight.SemiBold,
        modifier: Modifier = Modifier
    ) {
        ElevatedButton(
            onClick = onclick,
            enabled = isEnable,
            modifier = modifier
                .height(50.dp)
                .width(250.dp),
//            colors = ButtonColors(
//                containerColor = ,
//                contentColor = contentColor,
//                disabledContainerColor = disabledContainerColor,
//                disabledContentColor = disabledContentColor
//            )
        ) {
            Text(label, style = TextStyle(fontSize = fontSize, fontWeight = fontWeight))
        }

    }

    @Composable
    fun TextButtonLabel(
        label: String,
        onClick: () -> Unit,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight = FontWeight.W500,
        modifier: Modifier = Modifier
    ) {
        TextButton(onClick = onClick, modifier = modifier.fillMaxWidth()) {
            Text(label, style = TextStyle(fontSize = fontSize, fontWeight = fontWeight))
        }
    }
}