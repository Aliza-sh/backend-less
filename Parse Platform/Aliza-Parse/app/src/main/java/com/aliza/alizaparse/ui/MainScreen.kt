package com.aliza.alizaparse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.parse.ParseInstallation

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        // check installation =>
        ParseInstallation.getCurrentInstallation().saveInBackground()
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen()
}