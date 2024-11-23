package com.aliza.alizaparse.data

import com.parse.ParseUser

class MainRepository(
    private val apiService: ApiService
) {
    fun getCurrentUser(): ParseUser? {
        return apiService.getCurrentUser()
    }

    fun checkSession(): Boolean {
        return apiService.checkSession()
    }
}