package com.aliza.alizaparse.data

import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.net.ParseResponse
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    fun getCurrentUser(): ParseUser? {
        return try {
            ParseUser.getCurrentUser()?.fetch()
        } catch (e: ParseException) {
            null
        }
    }

    fun checkSession(): Boolean {
        return try {
            getCurrentUser()?.sessionToken != null

        } catch (e: ParseException) {
            false
        }
    }

    suspend fun signUp(username: String, email: String, password: String): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                val user = ParseUser().apply {
                    this.username = username
                    this.email = email
                    this.setPassword(password)
                }
                user.signUp()
                ParseResponse(user, null).generateResponse()
            } catch (e: ParseException) {
                ParseResponse<ParseUser>(null, e).generateResponse()
            } catch (e: Exception) {
                ParseRequest.Error<ParseUser>("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }
}