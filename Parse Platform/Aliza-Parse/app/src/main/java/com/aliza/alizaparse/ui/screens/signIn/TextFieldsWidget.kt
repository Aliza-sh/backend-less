package com.aliza.alizaparse.ui.screens.signIn

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.aliza.alizaparse.R

@Composable
fun TextFieldsWidget(
    username: String, onUsername: (String) -> Unit,
    password: String, onPassword: (String) -> Unit,
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = username,
        onValueChange = onUsername,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 18.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp, vertical = 8.dp),
        placeholder = {
            if (username.isEmpty()) {
                Text(
                    text = "User Name",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.dosis)),
                    )
                ) // نمایش hint فقط وقتی که متن خالی است
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(50)
    )

    OutlinedTextField(
        value = password,
        onValueChange = onPassword,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "",
                modifier = Modifier.padding(start = 18.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp, vertical = 8.dp),
        placeholder = {
            if (password.isEmpty()) {
                Text(
                    text = "Password",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.dosis)),
                    )
                ) // نمایش hint فقط وقتی که متن خالی است
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(50),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(R.drawable.ic_visibility)
            else painterResource(R.drawable.ic_visibility_off)

            IconButton(onClick = {
                passwordVisible = !passwordVisible
            }) {
                Icon(
                    painter = image,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

}