package com.example.myappbancodados

import android.app.Application
import com.example.myappbancodados.di.characterUseCaseModules
import com.example.myappbancodados.di.rickAndMortyRepository
import com.example.myappbancodados.di.viewModelModules

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)

            modules(viewModelModules, characterUseCaseModules, rickAndMortyRepository)
        }
    }
}