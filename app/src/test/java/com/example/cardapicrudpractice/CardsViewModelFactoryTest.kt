package com.example.cardapicrudpractice

import com.example.cardapicrudpractice.domain.usecase.AddCardUseCase
import com.example.cardapicrudpractice.domain.usecase.DeleteCardUseCase
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import com.example.cardapicrudpractice.domain.usecase.UpdateCardUseCase
import com.example.cardapicrudpractice.presentation.viewmodel.CardsViewModel
import com.example.cardapicrudpractice.presentation.viewmodel.CardsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class WalletCardsViewModelFactoryTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset the Main dispatcher after tests
        Dispatchers.resetMain()
    }

    @Test
    fun `factory creates WalletCardsViewModel`() {
        // Create dummy use case instances using mocks (replace GetCardsUseCase, etc. with your actual classes)
        val getUseCase = Mockito.mock(GetCardsUseCase::class.java)
        val addUseCase = Mockito.mock(AddCardUseCase::class.java)
        val updateUseCase = Mockito.mock(UpdateCardUseCase::class.java)
        val deleteUseCase = Mockito.mock(DeleteCardUseCase::class.java)

        val factory = CardsViewModelFactory(getUseCase, addUseCase, updateUseCase, deleteUseCase)
        val viewModel = factory.create(CardsViewModel::class.java)
        assertTrue(viewModel is CardsViewModel)
    }
}