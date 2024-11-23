package com.aliza.alizaparse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aliza.alizaparse.ui.MainScreen
import com.aliza.alizaparse.ui.theme.AlizaParseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlizaParseTheme {
                MainScreen()
            }
        }
    }
}

