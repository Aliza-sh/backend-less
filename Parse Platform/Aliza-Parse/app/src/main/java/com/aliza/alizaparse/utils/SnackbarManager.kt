package com.aliza.alizaparse.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SnackbarManager {

    // State to hold the SnackbarHostState
    private var snackbarHostState: SnackbarHostState? = null

    // A mutableState to control the alignment of Snackbar
    var snackbarAlignment by mutableStateOf(Alignment.BottomCenter)

    // Initialize the SnackbarManager with the host state
    fun init(snackbarHostState: SnackbarHostState) {
        this.snackbarHostState = snackbarHostState
    }

    // Function to show Snackbar
    fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        coroutineScope: CoroutineScope,
        alignment: Alignment = Alignment.BottomCenter,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        snackbarAlignment = alignment
        snackbarHostState?.let { state ->
            coroutineScope.launch {
                state.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration
                )
            }
        }
    }
}

@Composable
fun SnackbarWidget(
    snackbarData: SnackbarData,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color= MaterialTheme.colorScheme.onPrimary,
    actionColor: Color= MaterialTheme.colorScheme.onPrimary
) {
    Snackbar(
        modifier = Modifier.padding(8.dp),
        content = {
            Text(
                text = snackbarData.visuals.message,
                color = contentColor
            )
        },
        action = {
            snackbarData.visuals.actionLabel?.let { actionLabel ->
                TextButton(onClick = { snackbarData.performAction() }) {
                    Text(
                        text = actionLabel,
                        color = actionColor
                    )
                }
            }
        },
        containerColor = backgroundColor
    )
}

@Composable
fun SnackbarComposable(snackbarManager: SnackbarManager, modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Initialize SnackbarManager with the current host state
    snackbarManager.init(snackbarHostState)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = snackbarManager.snackbarAlignment) {
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { snackbarData ->
                SnackbarWidget(snackbarData)
            }
        )
    }
}

// Function to globally show Snackbar
fun showSnackbar(
    snackbarManager: SnackbarManager,
    message: String,
    actionLabel: String? = null,
    coroutineScope: CoroutineScope,
    alignment: Alignment = Alignment.BottomCenter,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    snackbarManager.showSnackbar(
        message = message,
        actionLabel = actionLabel,
        coroutineScope = coroutineScope,
        alignment = alignment,
        duration
    )
}