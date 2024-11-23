package com.aliza.alizaparse.ui.screens.signIn

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.HeaderSignWidget
import com.aliza.alizaparse.utils.SIGN_UP_SCREEN
import com.aliza.alizaparse.utils.SnackbarComposable
import com.aliza.alizaparse.utils.SnackbarManager
import com.aliza.alizaparse.utils.VERIFY_SCREEN
import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.showSnackbar
import com.aliza.alizaparse.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    viewModel: MainViewModel = koinViewModel(),
    onNavigateTo: (String) -> Unit,
) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()

    val snackbarManager = SnackbarManager()

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            launch {
                viewModel.signInState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loading = true
                        }

                        is ParseRequest.Success -> {
                            loading = false
                            onNavigateTo(VERIFY_SCREEN)
                        }

                        is ParseRequest.Error -> {
                            loading = false
                            val allError = value.error?.split(":")
                            val error = if (allError?.size!! > 1) {
                                allError[1]
                            } else
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

            launch {
                viewModel.resetPasswordState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loading = true
                        }

                        is ParseRequest.Success -> {
                            loading = false
                        }

                        is ParseRequest.Error -> {
                            loading = false
                            val allError = value.error?.split(":")
                            val error = if (allError?.size!! > 1) {
                                allError[1]
                            } else
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
    }


    SignInViews(
        userName, { userName = it },
        password, { password = it },
        forgetPass = {
            loading = true
            viewModel.resetPassword("alirezzzzzzzaaaaaaa@gmail.com")
            Log.e("SignInScreen", "SignInScreen: ")
        },
        onGoToSignIn = { onNavigateTo(SIGN_UP_SCREEN) },
        onSignUpClick = {
            loading = true
            viewModel.signIn(
                username = userName,
                password = password
            )
        },
        loading = loading,
        snackbarManager = snackbarManager
    )

}

@Composable
fun SignInViews(
    userName: String, onUserNameChange: (String) -> Unit,
    password: String, onPasswordChange: (String) -> Unit,
    forgetPass: () -> Unit,
    onSignUpClick: () -> Unit,
    onGoToSignIn: () -> Unit,
    loading: Boolean,
    snackbarManager: SnackbarManager
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            HeaderSignWidget()

            Text(
                text = "Sing in Now",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 100.dp, bottom = 28.dp)
                    .align(Alignment.CenterHorizontally)
            )

            TextFieldsWidget(
                userName, onUserNameChange,
                password, onPasswordChange,
            )

            ForgetWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
                    .padding(bottom = 136.dp),
                forgetPass = forgetPass
            )

            SignWidget(
                onSignUpClick = onSignUpClick,
                onGoToSignIn = onGoToSignIn,
                loading = loading
            )
        }
        SnackbarComposable(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            snackbarManager = snackbarManager,
        )
    }
}