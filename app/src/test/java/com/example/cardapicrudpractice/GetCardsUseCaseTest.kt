package com.example.cardapicrudpractice

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.repository.CardRepository
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class GetWalletCardsUseCaseTest {

    @Test
    fun `invoke returns wallet cards from repository`() = runTest {
        // Prepare dummy data
        val dummyCards = listOf(WalletCard(1, "Visa", "**** **** **** 1234", 1000.0))

        // Create a mock repository using Mockito
        val repository = mock(CardRepository::class.java)
        // Stub the suspend function to return dummyCards
        Mockito.`when`(repository.getWalletCards()).thenReturn(dummyCards)

        // Create the use case with the mocked repository
        val useCase = GetCardsUseCase(repository)
        val result = useCase.invoke()

        // Verify that the result is equal to dummyCards
        assertEquals(dummyCards, result)
    }
}
