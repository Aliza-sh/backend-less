package com.aliza.alizaparse.ui.screens.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.aliza.alizaparse.utils.SIGN_IN_SCREEN
import com.aliza.alizaparse.utils.SnackbarComposable
import com.aliza.alizaparse.utils.SnackbarManager
import com.aliza.alizaparse.utils.VERIFY_SCREEN
import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.showSnackbar
import com.aliza.alizaparse.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: MainViewModel = koinViewModel(),
    onNavigateTo: (String) -> Unit,
) {

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isTermsChecked by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()

    val snackbarManager = SnackbarManager()

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.signUpState.collect { value ->
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

    SignUpViews(
        userName, { userName = it },
        email, { email = it },
        password, { password = it },
        isTermsChecked, { isTermsChecked = it },
        onGoToSignIn = { onNavigateTo(SIGN_IN_SCREEN) },
        onSignUpClick = {
            loading = true
            viewModel.signUp(
                username = userName,
                email = email,
                password = password
            )
        },
        loading = loading,
        snackbarManager = snackbarManager
    )

}

@Composable
fun SignUpViews(
    userName: String, onUserNameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    password: String, onPasswordChange: (String) -> Unit,
    isTermsChecked: Boolean, onTermsChecked: (Boolean) -> Unit,
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
                "Create Account",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(top = 36.dp, bottom = 28.dp)
            )

            TextFieldsWidget(
                userName, onUserNameChange,
                email, onEmailChange,
                password, onPasswordChange,
            )

            TermsWidget(
                modifier = Modifier.padding(bottom = 136.dp),
                isTermsChecked, onTermsChecked
            )

            SignWidget(
                onSignUpClick = onSignUpClick,
                onGoToSignIn = onGoToSignIn,
                loading = loading
            )
        }
        SnackbarComposable(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
            snackbarManager = snackbarManager,
        )
    }

}