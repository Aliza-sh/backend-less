package com.aliza.alizaparse.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliza.alizaparse.data.MainRepository
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
}