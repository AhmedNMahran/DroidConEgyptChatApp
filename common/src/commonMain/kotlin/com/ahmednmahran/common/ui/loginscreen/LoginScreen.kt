package com.ahmednmahran.common.ui.loginscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ahmednmahran.common.data.model.ChatUser

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
    onButtonClick: (ChatUser) -> Unit = {}
){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.6f),
            value = username,
            onValueChange = {
                username = it
            })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.6f),
            value = password,
            onValueChange = {
                password = it
            })
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                onButtonClick(ChatUser(username, password))
                username  = ""
                password = ""
            }) {
            Text(" Login ")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}