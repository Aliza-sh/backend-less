package com.aliza.alizaparse.ui.screens.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.LoadingButton

@Composable
fun SignWidget(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onGoToSignIn: () -> Unit,
    loading: Boolean
) {
    LoadingButton(
        onClick = onSignUpClick,
        enabled = !loading,
        loading = loading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "SIGN IN",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.dosis)),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        )
    }

    Text(
        text = "Don't you have an account?",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.dosis)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        modifier = Modifier.padding(top = 28.dp)
    )


    Text(
        text = "Sing Up from here",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.dosis)),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable { onGoToSignIn() }
            .padding(8.dp)
    )
}