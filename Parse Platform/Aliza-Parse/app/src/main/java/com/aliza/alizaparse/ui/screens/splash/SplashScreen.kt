package com.aliza.alizaparse.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.aliza.alizaparse.utils.PROFILE_SCREEN
import com.aliza.alizaparse.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onNavigateTo: (String) -> Unit
) {

    val checkSession = viewModel.checkSession.collectAsState().value

    if (checkSession) {
        SplashWidget(onNavigateTo = {onNavigateTo(PROFILE_SCREEN)})
    } else {
        SplashWithSignWidget(onNavigateTo = onNavigateTo)
    }

}