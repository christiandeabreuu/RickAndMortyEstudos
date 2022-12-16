package com.example.myappbancodados.di

import com.example.myappbancodados.rickandmorty.domain.repository.RickAndMortyRepository
import com.example.myappbancodados.rickandmorty.domain.usecase.CharacterUseCase
import com.example.myappbancodados.rickandmorty.ui.character.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel { CharacterViewModel (get()) }
}
val characterUseCaseModules = module {
    single {  CharacterUseCase(get()) }
}
val rickAndMortyRepository = module {
    single { RickAndMortyRepository() }
}