package com.aliza.alizaparse.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.aliza.alizaparse.utils.SnackbarWidget
import com.aliza.alizaparse.utils.net.NetworkChecker
import com.parse.ParseInstallation
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    networkChecker: NetworkChecker = koinInject()
) {

    val nevHostController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var isNetworkAvailable by remember { mutableStateOf(true) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    SnackbarWidget(snackbarData = snackbarData)
                }
            )
        },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        // check installation =>
        ParseInstallation.getCurrentInstallation().saveInBackground()

        NavGraph(nevHostController)

        //network checking
        coroutineScope.launch {
            networkChecker.checkNetwork().collect {
                isNetworkAvailable = it
            }
        }
        if (!isNetworkAvailable)
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Check Internet Connection"
                )
            }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen()
}