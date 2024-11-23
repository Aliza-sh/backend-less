package com.aliza.alizaparse.ui.screens.profile

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.aliza.alizaparse.ui.component.NightSky
import com.aliza.alizaparse.ui.component.ShowDialog
import com.aliza.alizaparse.utils.SPLASH_SCREEN
import com.aliza.alizaparse.utils.SnackbarManager
import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.showSnackbar
import com.aliza.alizaparse.utils.toUri
import com.aliza.alizaparse.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: MainViewModel = koinViewModel(),
    onNavigateTo: (String) -> Unit
) {

    var initialFirstName by remember { mutableStateOf("") }
    var initialLastName by remember { mutableStateOf("") }
    var initialUserName by remember { mutableStateOf("") }
    var initialEmail by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var profilePic by remember { mutableStateOf<Uri?>(null) }

    var loadingPic by remember { mutableStateOf(false) }
    var loadingInfo by remember { mutableStateOf(false) }
    var saved by remember { mutableStateOf(false) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()
    val snackbarManager = SnackbarManager()

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
        loadingPic = true
        viewModel.loadProfilePicture()
    }

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            launch {
                viewModel.loadProfilePictureState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingPic = true
                        }

                        is ParseRequest.Success -> {
                            loadingPic = false
                            profilePic = value.data?.toUri()

                        }

                        is ParseRequest.Error -> {
                            loadingPic = false
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
                viewModel.uploadProfilePictureState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingPic = true
                        }

                        is ParseRequest.Success -> {
                            loadingPic = false
                            val user = value.data
                            profilePic = user?.getParseFile("profilePic")?.file?.toUri()
                        }

                        is ParseRequest.Error -> {
                            loadingPic = false
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
                viewModel.userInfoState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingInfo = true
                        }

                        is ParseRequest.Success -> {
                            loadingInfo = false
                            val user = value.data
                            // ذخیره مقادیر اولیه کاربر از سرور
                            initialFirstName = user?.getString("firstName") ?: ""
                            initialLastName = user?.getString("lastName") ?: ""
                            initialUserName = user?.username ?: ""
                            initialEmail = user?.email ?: ""

                            // تنظیم مقادیر جاری به مقدار اولیه
                            firstName = initialFirstName
                            lastName = initialLastName
                            userName = initialUserName
                            email = initialEmail
                        }

                        is ParseRequest.Error -> {
                            loadingInfo = false
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
                viewModel.updateUserInfoState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingInfo = true
                        }

                        is ParseRequest.Success -> {
                            loadingInfo = false
                            val user = value.data
                            // ذخیره مقادیر اولیه کاربر از سرور
                            initialFirstName = user?.getString("firstName") ?: ""
                            initialLastName = user?.getString("lastName") ?: ""
                            initialUserName = user?.username ?: ""
                            initialEmail = user?.email ?: ""

                            // تنظیم مقادیر جاری به مقدار اولیه
                            firstName = initialFirstName
                            lastName = initialLastName
                            userName = initialUserName
                            email = initialEmail
                        }

                        is ParseRequest.Error -> {
                            loadingInfo = false
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
                viewModel.logOutState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingInfo = true
                        }

                        is ParseRequest.Success -> {
                            loadingInfo = false
                        }

                        is ParseRequest.Error -> {
                            loadingInfo = false
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
                viewModel.deleteAccountState.collect { value ->
                    when (value) {
                        is ParseRequest.Idle -> {}

                        is ParseRequest.Loading -> {
                            loadingInfo = true
                        }

                        is ParseRequest.Success -> {
                            loadingInfo = false
                        }

                        is ParseRequest.Error -> {
                            loadingInfo = false
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

    saved = firstName != initialFirstName ||
            lastName != initialLastName ||
            userName != initialUserName ||
            email != initialEmail

    val galleryLauncher = ImageCropper { uri ->
        profilePic = uri
        loadingPic = true
        viewModel.uploadProfilePicture(uri.toFile())
    }

    var showLogOutDialog by remember { mutableStateOf(false) }
    var showDeleteAccDialog by remember { mutableStateOf(false) }
    ShowDialog(
        title = "Logout",
        description = "Do you want to log out of your account?",
        showDialog = showLogOutDialog,
        onShowDialog = { showLogOutDialog = it },
        confirmButton = {
            viewModel.logOut()
            onNavigateTo(SPLASH_SCREEN)
            showLogOutDialog = false
        },
        dismissButton = { showLogOutDialog = false }

    )
    ShowDialog(
        title = "Delete Account",
        description = "Are you sure you want to delete your account?",
        showDialog = showDeleteAccDialog,
        onShowDialog = { showDeleteAccDialog = it },
        confirmButton = {
            viewModel.deleteAccount()
            onNavigateTo(SPLASH_SCREEN)
            showDeleteAccDialog = false
        },
        dismissButton = { showDeleteAccDialog = false }
    )

    ProfileViews(
        profilePic = profilePic, onProfilePicChange = { galleryLauncher.launch("image/*") },
        firstName = firstName, onFirstNameChange = { firstName = it },
        lastName = lastName, onLastNameChange = { lastName = it },
        userName = userName, onUserNameChange = { userName = it },
        email = email, onEmailChange = { email = it },
        loadingPic = loadingPic, loadingInfo = loadingInfo,
        saved = saved, onSavedClick = {
            loadingInfo = true
            viewModel.updateUserInfo(
                firstName = firstName,
                lastName = lastName,
                userName = userName,
                email = email
            )
        },
        onLogoutClick = { showLogOutDialog = true },
        onDeleteAccountClick = { showDeleteAccDialog = true }
    )

}

@Composable
private fun ProfileViews(
    modifier: Modifier = Modifier,
    profilePic: Uri?, onProfilePicChange: () -> Unit,
    firstName: String, onFirstNameChange: (String) -> Unit,
    lastName: String, onLastNameChange: (String) -> Unit,
    userName: String, onUserNameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    loadingPic: Boolean, loadingInfo: Boolean,
    saved: Boolean, onSavedClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(320.dp)
                .align(Alignment.TopCenter)
        ) { NightSky(isAnimated = true) }

        Text(
            text = "Profile",
            style = TextStyle(
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                color = Color.White
            ),
            modifier = Modifier
                .padding(start = 16.dp, top = 90.dp)
        )

        UserInfoWidget(
            profilePic = profilePic, onProfilePicChange = onProfilePicChange,
            firstName = firstName, onFirstNameChange = onFirstNameChange,
            lastName = lastName, onLastNameChange = onLastNameChange,
            userName = userName, onUserNameChange = onUserNameChange,
            email = email, onEmailChange = onEmailChange,
            loadingPic = loadingPic
        )

        BottomButtonsWidget(
            modifier
                .fillMaxWidth()
                .padding(end = 24.dp, start = 24.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter),
            onSavedClick,
            loadingInfo,
            saved,
            onLogoutClick,
            onDeleteAccountClick
        )

    }
}
