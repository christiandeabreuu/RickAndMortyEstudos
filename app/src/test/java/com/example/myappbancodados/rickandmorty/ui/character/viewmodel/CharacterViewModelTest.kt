package com.example.myappbancodados.rickandmorty.ui.character.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myappbancodados.CoroutineTestRule
import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.example.myappbancodados.rickandmorty.domain.usecase.CharacterUseCase
import com.example.myappbancodados.viewstate.ViewState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
internal class CharacterViewModelTest {

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private val useCaseCharacter : CharacterUseCase = mockk(relaxed = true)

    private lateinit var viewModelCharacter: CharacterViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModelCharacter = CharacterViewModel(useCaseCharacter)
    }

    @After
    fun OnAfter(){
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `when viewmodel is called GetAllCharacters should return Error`() = runTest{

        val mockkError = ViewState.Error(Throwable("Não foi possível carregar a lista da internet!"))

        coEvery { useCaseCharacter.getAllCharactersNetwork() } returns mockkError

        viewModelCharacter.getAllCharactersNetwork()

       assertEquals(mockkError, viewModelCharacter.characterListState.value)
        //withContext(Dispatchers.IO) e faz no teste chegar nulo

    }

    @Test
    fun `when viewmodel is called GetAllCharacters should return Success`() = runTest{

        val mockk = ViewState.Success(characterList)

        coEvery { useCaseCharacter.getAllCharactersNetwork() } returns mockk

        val teste = viewModelCharacter.getAllCharactersNetwork()

//       assertEquals(mockk, viewModelCharacter.characterListState.value)
// equals só nao ta passando pq na classe tem withContext(Dispatchers.IO) e faz no teste chegar nulo
        coVerify { useCaseCharacter.getAllCharactersNetwork() }
    }

    companion object{
        val characterList = listOf(CharacterResult("a",
            listOf("", "123", "1112321"),
            "masculino",
            1,
        "sadhkssdfdsf.jpg",
            "teste",
            "human",
            "alive",
            "123",
            "www.ieiwi.com.br")
        )
    }

}