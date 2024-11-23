package com.aliza.alizaparse.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserFormWidget(
    modifier: Modifier = Modifier,
    firstName: String, onFirstNameChange: (String) -> Unit,
    lastName: String, onLastNameChange: (String) -> Unit,
    userName: String, onUserNameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = "First Name",
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, bottom = 4.dp)
        )

        BasicTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth()
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = "Last Name",
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, bottom = 4.dp)
        )

        BasicTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth()
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = "User Name",
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, bottom = 4.dp)
        )

        BasicTextField(
            value = userName,
            onValueChange = onUserNameChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth()
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = "E-mail",
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, bottom = 4.dp)
        )

        BasicTextField(
            value = email,
            onValueChange = onEmailChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth()
        )
    }

}