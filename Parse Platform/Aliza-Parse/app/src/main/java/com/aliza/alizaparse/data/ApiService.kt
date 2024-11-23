package com.aliza.alizaparse.data

import com.parse.ParseException
import com.parse.ParseUser

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
}