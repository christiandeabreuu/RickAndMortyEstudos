package com.example.myappbancodados.rickandmorty.data.remote

import com.example.myappbancodados.rickandmorty.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getAllCharactersNetwork(): CharacterResponse


}