package com.example.myappbancodados.rickandmorty.domain.repository

import com.example.myappbancodados.rickandmorty.data.model.CharacterResponse
import com.example.myappbancodados.rickandmorty.data.remote.RetrofitService

class RickAndMortyRepository() {

//    private val characterDAO: CharacterDAO
//    private val characterDAO =  CharacterDatabase.getDatabase(context).characterDAO()
    suspend fun getAllCharactersNetwork(): CharacterResponse {
        return RetrofitService.apiService.getAllCharactersNetwork()
    }

//    suspend fun insertAllCharactersDB(listCharacter: List<CharacterResult>) =
//        characterDAO.insertAllCharactersDB(listCharacter)
}