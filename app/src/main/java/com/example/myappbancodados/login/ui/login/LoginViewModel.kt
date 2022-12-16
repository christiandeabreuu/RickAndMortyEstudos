package com.example.myappbancodados.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.login.domain.repository.AuthenticationRepository
import com.example.myappbancodados.login.domain.usecase.LoginUseCase

class LoginViewModel: ViewModel() {
    private val repository = AuthenticationRepository()
    private val loginUseCase = LoginUseCase()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User) {
        when {
            user.email.isEmpty() -> _errorState.value = "Insira um email"
            user.password.isEmpty() -> _errorState.value = "Insira uma senha"
            else -> loginUser(user)
        }
    }

    private fun loginUser(user: User) {
        try {
            repository.loginUser(user.email, user.password).addOnSuccessListener {

                _loginState.value = user


            }.addOnFailureListener {
                _errorState.value = "Ops! Ocorreu um erro ao criar o usuário!" + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}