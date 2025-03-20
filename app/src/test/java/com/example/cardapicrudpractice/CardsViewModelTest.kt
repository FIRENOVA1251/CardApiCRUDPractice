package com.example.cardapicrudpractice

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.usecase.AddCardUseCase
import com.example.cardapicrudpractice.domain.usecase.DeleteCardUseCase
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import com.example.cardapicrudpractice.domain.usecase.UpdateCardUseCase
import com.example.cardapicrudpractice.presentation.viewmodel.CardsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CardsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after tests
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCards updates stateFlow with cards`() = runTest {
        // Prepare dummy data
        val dummyCards = listOf(WalletCard(1, "Visa", "**** **** **** 1234", 1000.0))

        // Use Mockito to create and configure the GetCardsUseCase mock
        val getUseCase = Mockito.mock(GetCardsUseCase::class.java)
        // Configure the suspend function to return dummyCards
        Mockito.`when`(getUseCase.invoke()).thenReturn(dummyCards)

        // Create mocks for the other use cases
        val addUseCase = Mockito.mock(AddCardUseCase::class.java)
        val updateUseCase = Mockito.mock(UpdateCardUseCase::class.java)
        val deleteUseCase = Mockito.mock(DeleteCardUseCase::class.java)

        // Create the ViewModel with the mocked use cases
        val viewModel = CardsViewModel(getUseCase, addUseCase, updateUseCase, deleteUseCase)

        // Trigger fetching of cards and advance time until all coroutines complete
        viewModel.fetchWalletCards()
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the stateFlow is updated with the dummy data
        assertEquals(dummyCards, viewModel.walletCards.value)
    }
}