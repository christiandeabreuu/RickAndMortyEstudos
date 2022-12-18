package com.example.myappbancodados.rickandmorty.ui.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.example.myappbancodados.rickandmorty.domain.usecase.CharacterUseCase
import com.example.myappbancodados.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(private val characterUseCase : CharacterUseCase) : ViewModel() {

    private val _characterListState = MutableStateFlow<ViewState<List<CharacterResult>>>(ViewState.Loading())
    val characterListState: StateFlow<ViewState<List<CharacterResult>>> get() = _characterListState

    val loading = MutableLiveData<ViewState<Boolean>>()

    fun getAllCharactersNetwork() {
//        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharactersNetwork()
                }
                _characterListState.value = response
            } catch (ex: Exception) {
                _characterListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista"))
            }finally {
//                loading.value = ViewState.Loading(false)
            }
        }
    }
}