package com.example.myappbancodados.rickandmorty.domain.repository

import com.example.myappbancodados.rickandmorty.data.model.CharacterResponse
import com.example.myappbancodados.rickandmorty.data.remote.RetrofitService

class RickAndMortyRepository {
    suspend fun getAllCharactersNetwork(): CharacterResponse {
        return RetrofitService.apiService.getAllCharactersNetwork()
    }
}