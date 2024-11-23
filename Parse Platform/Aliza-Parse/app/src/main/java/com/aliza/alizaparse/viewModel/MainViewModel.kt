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
}