package ru.easycode.words504.languages.data.cloud

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.easycode.words504.domain.HandleError

interface SignInDataSource {

    suspend fun checkSignIn(): GoogleSignInAccount

    class Base(
        private val context: Context,
        private val errorHandler: HandleError<Exception, Throwable>
    ) : SignInDataSource {
        override suspend fun checkSignIn(): GoogleSignInAccount {
            try {
                return GoogleSignIn.getLastSignedInAccount(context)!!
            } catch (e: Exception) {
                throw errorHandler.handle(e)
            }
        }
    }
}
