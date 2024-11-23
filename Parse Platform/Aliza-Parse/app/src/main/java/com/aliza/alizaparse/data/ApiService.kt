package com.aliza.alizaparse.data

import com.aliza.alizaparse.utils.net.ParseRequest
import com.aliza.alizaparse.utils.net.ParseResponse
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

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

    suspend fun signIn(username: String, password: String): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                val user = ParseUser.logIn(username, password)
                ParseResponse(user, null).generateResponse()
            } catch (e: ParseException) {
                ParseResponse(null, e).generateResponse()
            } catch (e: Exception) {
                ParseRequest.Error<ParseUser>("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun resetPassword(email: String): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                ParseUser.requestPasswordReset(email)
                ParseResponse<ParseUser>(null, null).generateResponse()
            } catch (e: ParseException) {
                ParseResponse<ParseUser>(null, e).generateResponse()
            } catch (e: Exception) {
                ParseRequest.Error<ParseUser>("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun checkEmailVerified(): ParseRequest<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val user = getCurrentUser()
                if (user!!.getBoolean("emailVerified")) {
                    ParseRequest.Success(true)
                } else {
                    ParseRequest.Error("Email not verified yet.")
                }
            } catch (e: ParseException) {
                ParseRequest.Error("Error checking email verification: ${e.localizedMessage}")
            } catch (e: Exception) {
                ParseRequest.Error("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun getUserProfilePicture(): ParseRequest<File> {
        return withContext(Dispatchers.IO) {
            try {
                val profilePicture =
                    getCurrentUser()?.getParseFile("profilePic") // assuming "profilePicture" is the column name
                if (profilePicture != null) {
                    val file = profilePicture.file
                    ParseRequest.Success(file)
                } else {
                    ParseRequest.Error("No profile picture found.")
                }
            } catch (e: ParseException) {
                ParseRequest.Error("Failed to retrieve profile picture: ${e.localizedMessage}")
            } catch (e: Exception) {
                ParseRequest.Error("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun uploadProfilePicture(file: File): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                val parseFile = ParseFile(file)
                parseFile.save() // ذخیره فایل در Parse

                val currentUser = getCurrentUser()
                currentUser?.put("profilePic", parseFile)
                currentUser?.save()

                ParseResponse(currentUser, null).generateResponse()
            } catch (e: ParseException) {
                ParseResponse<ParseUser>(null, e).generateResponse()
            } catch (e: Exception) {
                ParseRequest.Error<ParseUser>("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun getUserInfo(): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                ParseResponse<ParseUser>(getCurrentUser(), null).generateResponse()
            } catch (e: ParseException) {
                ParseResponse<ParseUser>(null, e).generateResponse()
            } catch (e: Exception) {
                ParseRequest.Error<ParseUser>("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun updateUserInfo(
        firstName: String,
        lastName: String,
        userName: String,
        email: String
    ): ParseRequest<ParseUser> {
        return withContext(Dispatchers.IO) {
            try {
                val currentUser = getCurrentUser()

                if (currentUser != null) {
                    currentUser.put("firstName", firstName)
                    currentUser.put("lastName", lastName)
                    currentUser.username = userName
                    currentUser.email = email

                    currentUser.save()

                    ParseResponse(currentUser, null).generateResponse()
                } else {
                    ParseRequest.Error("No user is logged in.")
                }
            } catch (e: ParseException) {
                ParseResponse<ParseUser>(null, e).generateResponse()
            }
        }
    }

    suspend fun logOut(): ParseRequest<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                ParseUser.logOut()
                ParseRequest.Success(true)
            } catch (e: ParseException) {
                ParseRequest.Error("Failed to logOut: ${e.localizedMessage}")
            } catch (e: Exception) {
                ParseRequest.Error("An unknown error occurred: ${e.localizedMessage}")
            }
        }

    }

    suspend fun deleteAccount(): ParseRequest<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                getCurrentUser()?.delete()
                ParseUser.logOut()
                ParseRequest.Success(true)
            } catch (e: ParseException) {
                ParseRequest.Error("Failed to delete account: ${e.localizedMessage}")
            } catch (e: Exception) {
                ParseRequest.Error("An unknown error occurred: ${e.localizedMessage}")
            }
        }
    }
}