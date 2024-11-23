package com.aliza.alizaparse.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import com.aliza.alizaparse.ui.theme.Red

@Composable
fun ShowDialog(
    showDialog: Boolean, onShowDialog: (Boolean) -> Unit,
    confirmButton: () -> Unit, dismissButton: () -> Unit,
    title: String,
    description: String,
    containerColor: Color = MaterialTheme.colorScheme.background,
    titleContentColor: Color = MaterialTheme.colorScheme.onBackground,
    textContentColor: Color = MaterialTheme.colorScheme.onBackground,
) {

    Button(onClick = { onShowDialog.invoke(true) }) {
        Text("Show Dialog")
    }

    if (showDialog) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { onShowDialog.invoke(false) },
            title = { Text(text = title) },
            text = { Text(description) },
            confirmButton = {
                Button(
                    onClick = confirmButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = dismissButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) {
                    Text(
                        text = "Dismiss",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            containerColor = containerColor,
            titleContentColor = titleContentColor,
            textContentColor = textContentColor
        )
    }
}
