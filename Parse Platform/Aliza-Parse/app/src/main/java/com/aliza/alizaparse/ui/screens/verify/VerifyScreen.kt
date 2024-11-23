package com.aliza.alizaparse.ui.screens.verify

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.NightSky
import com.aliza.alizaparse.utils.PROFILE_SCREEN
import com.aliza.alizaparse.utils.SnackbarComposable
import com.aliza.alizaparse.utils.SnackbarManager
import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.showSnackbar
import com.aliza.alizaparse.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifyScreen(
    viewModel: MainViewModel = koinViewModel(),
    onNavigateTo: (String) -> Unit,
) {
    var loading by remember { mutableStateOf(false) }
    val otpCount = 4
    var otpValue by remember { mutableStateOf("") }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()
    val snackbarManager = SnackbarManager()

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.emailVerifiedState.collect { value ->
                when (value) {
                    is ParseRequest.Idle -> {}

                    is ParseRequest.Loading -> {
                        loading = true
                    }

                    is ParseRequest.Success -> {
                        loading = false
                        onNavigateTo(PROFILE_SCREEN)
                    }

                    is ParseRequest.Error -> {
                        loading = false
                        val allError = value.error?.split(":")
                        val error = if (allError?.size!! > 1) {
                            allError[1]
                        }else
                            allError[0]
                        showSnackbar(
                            snackbarManager = snackbarManager,
                            message = error,
                            coroutineScope = coroutineScope,
                            alignment = Alignment.BottomCenter // Change position
                        )
                    }
                }
            }
        }
    }

    VerifyViews(
        onBackClick = { onNavigateTo("Back") },
        loading = loading,
        otpValue = otpValue,
        otpCount = otpCount,
        onOtpValueChange = { value, otpInputFilled ->
            otpValue = value
            if (otpValue.length == otpCount)
                viewModel.checkEmailVerified()
            Log.e("VerifyScreen", "VerifyScreen: $value")
        },
        snackbarManager = snackbarManager
    )
}

@Composable
fun VerifyViews(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    loading: Boolean,
    otpCount: Int = 4,
    otpValue: String, onOtpValueChange: (String, Boolean) -> Unit,
    snackbarManager: SnackbarManager

) {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(0.dp))
        ) { NightSky() }

        IconButton(
            modifier = Modifier.padding(top = 36.dp, start = 8.dp),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        HeaderWidget(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 54.dp)
        )

        VerificationBoxWidget(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 200.dp)
                .align(Alignment.BottomCenter),
            loading = loading,
            otpValue = otpValue,
            otpCount = otpCount,
            onOtpValueChange = onOtpValueChange
        )

        SnackbarComposable(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            snackbarManager = snackbarManager,
        )
    }
}