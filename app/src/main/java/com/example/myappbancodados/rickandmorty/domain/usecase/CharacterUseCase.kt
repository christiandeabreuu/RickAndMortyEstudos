package com.example.myappbancodados.rickandmorty.domain.usecase

import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.example.myappbancodados.rickandmorty.domain.repository.RickAndMortyRepository
import com.example.myappbancodados.viewstate.ViewState

class CharacterUseCase(private val repository : RickAndMortyRepository) {

    suspend fun getAllCharactersNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val character = repository.getAllCharactersNetwork()
            ViewState.Success(character.results)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possivel carregar a lista de characters"))
        }
    }
}