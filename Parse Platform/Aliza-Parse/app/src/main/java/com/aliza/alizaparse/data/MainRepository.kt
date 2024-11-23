package com.aliza.alizaparse.data

import com.aliza.alizaparse.utils.net.ParseRequest
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

    suspend fun signUp(username: String, email: String, password: String): ParseRequest<ParseUser> {
        return apiService.signUp(username, email, password)
    }

    suspend fun signIn(username: String, password: String): ParseRequest<ParseUser> {
        return apiService.signIn(username, password)
    }

    suspend fun resetPassword(email: String): ParseRequest<ParseUser> {
        return apiService.resetPassword(email)
    }

}