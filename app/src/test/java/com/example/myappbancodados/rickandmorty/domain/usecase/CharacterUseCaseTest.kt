package com.example.myappbancodados.rickandmorty.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myappbancodados.CoroutineTestRule
import com.example.myappbancodados.rickandmorty.domain.repository.RickAndMortyRepository
import com.example.myappbancodados.rickandmorty.ui.character.viewmodel.CharacterViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule


@ExperimentalCoroutinesApi
internal class CharacterUseCaseTest{

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private val repository : RickAndMortyRepository = mockk(relaxed = true)

    private lateinit var useCaseCharacter: CharacterUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCaseCharacter = CharacterUseCase(repository)
    }

    @After
    fun OnAfter(){
        Dispatchers.resetMain()
        unmockkAll()
    }


}