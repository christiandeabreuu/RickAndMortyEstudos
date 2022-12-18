package com.example.myappbancodados.login.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.login.domain.repository.AuthenticationRepository
import com.example.myappbancodados.viewstate.ViewState

class LoginUseCase {
    private val repository = AuthenticationRepository()
    var state: ViewState<User>? = null
//    fun loginUser(user: User): ViewState<User> {
//
//        try {
//            repository.loginUser(user.email, user.password).addOnSuccessListener {
//                state = ViewState.Success(user)
//
//            }.addOnFailureListener {
//                state = ViewState.Error(Throwable("erro de usuario e senha"))
//            }
//        } catch (ex: Exception) {
//            state = ViewState.Error(Throwable("Exception, erro de internet"))
//        }
//        return state
//            ?: throw IllegalStateException("erro nao previsto, mas eu acho que nao aconteceira, pq só aconteceria se nao entrasse no try e no catch")
//    }

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    private fun loginUser(user: User) {
        try {
            repository.loginUser(
                user.email,
                user.password
            ).addOnSuccessListener {
                _loginState.value = user
            }.addOnFailureListener {
                _errorState.value = "LOGIN_ERROR_MESSAGE" + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}





