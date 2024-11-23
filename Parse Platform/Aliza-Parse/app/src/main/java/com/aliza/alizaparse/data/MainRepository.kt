package com.aliza.alizaparse.data

import com.aliza.alizaparse.utils.net.ParseRequest
import com.parse.ParseUser
import java.io.File

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

    suspend fun checkEmailVerified(): ParseRequest<Boolean> {
        return apiService.checkEmailVerified()
    }

    suspend fun getUserProfilePicture(): ParseRequest<File> {
        return apiService.getUserProfilePicture()
    }

    suspend fun uploadProfilePicture(file: File): ParseRequest<ParseUser> {
        return apiService.uploadProfilePicture(file)
    }

    suspend fun getUserInfo(): ParseRequest<ParseUser> {
        return apiService.getUserInfo()
    }

    suspend fun updateUserInfo(
        firstName: String,
        lastName: String,
        userName: String,
        email: String
    ): ParseRequest<ParseUser> {
        return apiService.updateUserInfo(firstName, lastName, userName, email)
    }

    suspend fun logOut(): ParseRequest<Boolean> {
        return apiService.logOut()
    }

    suspend fun deleteAccount(): ParseRequest<Boolean> {
        return apiService.deleteAccount()
    }

}