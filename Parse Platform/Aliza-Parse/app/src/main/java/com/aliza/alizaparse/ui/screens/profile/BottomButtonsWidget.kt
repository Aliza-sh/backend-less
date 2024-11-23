package com.aliza.alizaparse.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.LoadingButton
import com.aliza.alizaparse.ui.theme.Red

@Composable
fun BottomButtonsWidget(
    modifier: Modifier,
    onSavedClick: () -> Unit,
    loading: Boolean,
    saved: Boolean,
    onLogoutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit
) {
    Column(modifier = modifier) {

        LoadingButton(
            onClick = onSavedClick,
            enabled = if (loading || !saved) false else true,
            loading = loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Saved",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = onLogoutClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.5f)
            ) {
                Text(text = "Logout")
            }

            Button(
                onClick = onDeleteAccountClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                ),
                modifier = Modifier.weight(0.5f)
            ) {
                Text(text = "Delete Account")
            }
        }
    }
}