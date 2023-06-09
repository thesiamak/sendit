package com.drax.sendit.view.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.drax.sendit.view.login.usecases.ForgetPasswordUseCae
import com.drax.sendit.view.login.usecases.GoogleSsoUseCase
import com.drax.sendit.view.login.usecases.LoginUseCase
import com.drax.sendit.view.login.usecases.RegisterUseCase
import com.drax.sendit.view.util.job
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val forgetPasswordUseCae: ForgetPasswordUseCae,
    private val registerUseCase: RegisterUseCase,
    private val ssoUseCase: GoogleSsoUseCase,
) : ViewModel() {

    val formState = mutableStateOf<FormState>(FormState.Invalid)
    val formType = mutableStateOf<FormType>(FormType.Login)
    val usernameInput = mutableStateOf("")
    val passwordInput = mutableStateOf("")
    val passwordRepeatInput = mutableStateOf("")

    fun register() {
        job {
            registerUseCase(
                formState = formState,
                username = usernameInput.value,
                password = passwordInput.value,
                passwordRepeat = passwordRepeatInput.value
            )
        }
    }

    fun loginWithEmail() {
        job {
            loginUseCase(
                formState = formState,
                username = usernameInput.value,
                password = passwordInput.value
            )
        }
    }

    fun loginWithGoogle(tokenId: String) {
        job {
            ssoUseCase(
                formState = formState,
                username = usernameInput.value,
                idToken = tokenId
            )
        }
    }

    fun forgetPassword() {
        job {
            forgetPasswordUseCae(formState = formState, email = usernameInput.value)
        }
    }

    fun validateInput(usernameInput: String, pattern: Pattern): Boolean {
        return usernameInput.matches(pattern.toRegex())
    }
}
