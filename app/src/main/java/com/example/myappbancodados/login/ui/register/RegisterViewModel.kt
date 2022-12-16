package com.example.myappbancodados.login.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.login.domain.repository.AuthenticationRepository
import com.example.myappbancodados.login.domain.usecase.RegisterUseCase

class RegisterViewModel : ViewModel() {

    private val authenticationRepository = AuthenticationRepository()
    private val registerUseCase = RegisterUseCase()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User) {
        when {
            user.name.isEmpty() -> _errorState.value = "Insira seu nome"
            user.email.isEmpty() -> _errorState.value = "Insira um email"
            user.password.isEmpty() -> _errorState.value = "Insira uma senha"
            else -> registerUser(user)
        }
    }

    private fun registerUser(user: User) {
        try {
            authenticationRepository.registerUser(user.email, user.password).addOnSuccessListener {
                authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
                    _registerState.value = user
                }
            }.addOnFailureListener {
                _errorState.value = "Ops! Ocorreu um erro ao criar o usuário!"
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}