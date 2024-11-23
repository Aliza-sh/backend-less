package com.aliza.alizaparse.utils.net

import com.parse.ParseException

open class ParseResponse<T>(private val parseResult: T?, private val parseError: ParseException?) {

    fun generateResponse(): ParseRequest<T> {
        return when {
            parseError != null -> {
                when (parseError.code) {
                    ParseException.OBJECT_NOT_FOUND -> ParseRequest.Error("User not found")
                    ParseException.CONNECTION_FAILED -> ParseRequest.Error("Connection failed. Please check your network.")
                    ParseException.INVALID_SESSION_TOKEN -> ParseRequest.Error("Invalid session token. Please log in again.")
                    ParseException.EMAIL_TAKEN -> ParseRequest.Error("This email is already taken.")
                    ParseException.TIMEOUT -> ParseRequest.Error("Request timed out. Try again.")
                    else -> ParseRequest.Error(parseError.localizedMessage ?: "An unknown error occurred")
                }
            }
            parseResult != null -> ParseRequest.Success(parseResult)
            else -> ParseRequest.Error("Unknown error")
        }
    }
}