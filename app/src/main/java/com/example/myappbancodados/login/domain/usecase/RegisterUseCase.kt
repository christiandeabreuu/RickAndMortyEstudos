package com.example.myappbancodados.login.domain.usecase

import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.login.domain.repository.AuthenticationRepository

class RegisterUseCase {
    private val authenticationRepository = AuthenticationRepository()

    private fun registerUser(user: User) {
        authenticationRepository.registerUser(user.email, user.password).addOnSuccessListener {
            authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
            }
        }
    }
}

