package com.thread_jetpack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thread_jetpack.component.Constants.ALREADY_REGISTERED
import com.thread_jetpack.component.Constants.BIO_LABEL
import com.thread_jetpack.component.Constants.EMAIL_LABEL
import com.thread_jetpack.component.Constants.NAME_LABEL
import com.thread_jetpack.component.Constants.PASSWORD_LABEL
import com.thread_jetpack.component.Constants.REGISTER_HERE
import com.thread_jetpack.component.Constants.REGISTER_NOW
import com.thread_jetpack.component.Constants.USER_NAME_LABEL
import com.thread_jetpack.component.UiComponent.ElevatedButtonUi
import com.thread_jetpack.component.UiComponent.OutLineTextFieldBox
import com.thread_jetpack.component.UiComponent.TextButtonLabel
import com.thread_jetpack.navigation.Routes


@Composable
fun Register(navController: NavHostController) {
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(REGISTER_HERE, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))

        Spacer(modifier = Modifier.height(12.dp))

        // here we will be checking weather email id is unique or not
        OutLineTextFieldBox(email, { email = it }, EMAIL_LABEL, KeyboardType.Email)
        OutLineTextFieldBox(
            password,
            { password = it },
            PASSWORD_LABEL,
            KeyboardType.Password,
            true
        )
        OutLineTextFieldBox(userName, { userName = it }, USER_NAME_LABEL)
        OutLineTextFieldBox(name, { name = it }, NAME_LABEL)
        OutLineTextFieldBox(bio, { bio = it }, BIO_LABEL)

        Spacer(Modifier.height(10.dp))
        ElevatedButtonUi(REGISTER_NOW, onclick = {

        })

        Spacer(Modifier.height(10.dp))
        TextButtonLabel(ALREADY_REGISTERED,{
            // removing the register page on pressing again again on login and register
            navController.navigate(Routes.Login.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        })
    }
}
