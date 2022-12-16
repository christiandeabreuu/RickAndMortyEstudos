package com.example.myappbancodados.login.domain.usecase

import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.login.domain.repository.AuthenticationRepository

class LoginUseCase {
    private val repository = AuthenticationRepository()

    private fun loginUser(user: User) =
        repository.loginUser(user.email, user.password).addOnSuccessListener {
        }
}