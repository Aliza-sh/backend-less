package com.aliza.alizaparse.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliza.alizaparse.data.MainRepository
import com.aliza.alizaparse.utils.isValidEmail
import com.aliza.alizaparse.utils.isValidPassword
import com.aliza.alizaparse.utils.net.ParseRequest
import com.parse.ParseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            delay(400)
            checkSession()
        }
    }

    private val _checkSession = MutableStateFlow(false)
    val checkSession: StateFlow<Boolean> = _checkSession
    private fun checkSession() {
        _checkSession.value = repository.checkSession()
    }

    private val _signUpState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val signUpState: StateFlow<ParseRequest<ParseUser>> = _signUpState
    fun signUp(username: String, password: String, email: String) {

        if (username.isBlank()) {
            _signUpState.value = ParseRequest.Error("Username cannot be empty")
            return
        }
        if (!email.isValidEmail()) {
            _signUpState.value = ParseRequest.Error("Invalid email format.")
            return
        }
        if (!password.isValidPassword()) {
            _signUpState.value = ParseRequest.Error("Password must be at least 6 characters.")
            return
        }

        viewModelScope.launch {
            _signUpState.value = ParseRequest.Loading()
            val result = repository.signUp(username, email, password)
            _signUpState.value = result
        }
    }

    private val _signInState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val signInState: StateFlow<ParseRequest<ParseUser>> = _signInState
    fun signIn(username: String, password: String) {

        if (username.isBlank()) {
            _signInState.value = ParseRequest.Error("Username cannot be empty")
            return
        }
        if (!password.isValidPassword()) {
            _signInState.value = ParseRequest.Error("Password must be at least 6 characters.")
            return
        }

        viewModelScope.launch {
            _signInState.value = ParseRequest.Loading()
            val result = repository.signIn(username, password)
            _signInState.value = result
        }
    }

    private val _resetPasswordState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val resetPasswordState: StateFlow<ParseRequest<ParseUser>> = _resetPasswordState
    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = ParseRequest.Loading()
            val result = repository.resetPassword(email)
            _resetPasswordState.value = result
        }
    }

    private val _emailVerifiedState = MutableStateFlow<ParseRequest<Boolean>>(ParseRequest.Idle())
    val emailVerifiedState: StateFlow<ParseRequest<Boolean>> = _emailVerifiedState
    fun checkEmailVerified() {
        viewModelScope.launch {
            _emailVerifiedState.value = ParseRequest.Loading()
            val result = repository.checkEmailVerified()
            _emailVerifiedState.value = result
        }
    }

    private val _loadProfilePictureState = MutableStateFlow<ParseRequest<File>>(ParseRequest.Idle())
    val loadProfilePictureState: StateFlow<ParseRequest<File>> = _loadProfilePictureState
    fun loadProfilePicture() {
        viewModelScope.launch {
            _loadProfilePictureState.value = ParseRequest.Loading()
            val result = repository.getUserProfilePicture()
            _loadProfilePictureState.value = result
        }
    }

    private val _uploadProfilePictureState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val uploadProfilePictureState: StateFlow<ParseRequest<ParseUser>> = _uploadProfilePictureState
    fun uploadProfilePicture(file: File) {
        viewModelScope.launch {
            _uploadProfilePictureState.value = ParseRequest.Loading()
            val result = repository.uploadProfilePicture(file)
            _uploadProfilePictureState.value = result
        }
    }

    private val _userInfoState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val userInfoState: StateFlow<ParseRequest<ParseUser>> = _userInfoState
    fun getUserInfo() {
        viewModelScope.launch {
            _userInfoState.value = ParseRequest.Loading()
            val result = repository.getUserInfo()
            _userInfoState.value = result
        }
    }

    private val _updateUserInfoState = MutableStateFlow<ParseRequest<ParseUser>>(ParseRequest.Idle())
    val updateUserInfoState: StateFlow<ParseRequest<ParseUser>> = _updateUserInfoState
    fun updateUserInfo(
        firstName: String,
        lastName: String,
        userName: String,
        email: String
    ) {
        viewModelScope.launch {
            _updateUserInfoState.value = ParseRequest.Loading()
            val result = repository.updateUserInfo(firstName, lastName, userName, email)
            _updateUserInfoState.value = result
        }
    }

    private val _logOutState = MutableStateFlow<ParseRequest<Boolean>>(ParseRequest.Idle())
    val logOutState: StateFlow<ParseRequest<Boolean>> = _logOutState
    fun logOut() {
        viewModelScope.launch {
            _logOutState.value = ParseRequest.Loading()
            val result = repository.logOut()
            _logOutState.value = result
        }
    }

    private val _deleteAccountState = MutableStateFlow<ParseRequest<Boolean>>(ParseRequest.Idle())
    val deleteAccountState: StateFlow<ParseRequest<Boolean>> = _deleteAccountState
    fun deleteAccount() {
        viewModelScope.launch {
            _deleteAccountState.value = ParseRequest.Loading()
            val result = repository.deleteAccount()
            _deleteAccountState.value = result
        }
    }
}