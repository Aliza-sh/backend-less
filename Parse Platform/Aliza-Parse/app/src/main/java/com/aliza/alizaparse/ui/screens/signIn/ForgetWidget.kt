package com.aliza.alizaparse.ui.screens.signIn

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.aliza.alizaparse.R

@Composable
fun ForgetWidget(
    modifier: Modifier,
    forgetPass: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        TextButton(
            onClick = forgetPass,
        ) {
            Text(
                text = "Forget Password?",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}