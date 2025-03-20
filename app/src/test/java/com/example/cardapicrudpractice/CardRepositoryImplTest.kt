package com.example.cardapicrudpractice


import com.example.cardapicrudpractice.data.CardRepositoryImpl
import com.example.cardapicrudpractice.data.FakeWalletCardApi
import com.example.cardapicrudpractice.domain.model.WalletCard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CardRepositoryImplTest {

    private val fakeApi = FakeWalletCardApi()
    private val repository = CardRepositoryImpl(fakeApi)

    @Test
    fun `getWalletCards returns list of wallet cards`() = runTest {
        val cards = repository.getWalletCards()
        // The fake API initially returns 3 wallet cards
        assertEquals(3, cards.size)
    }

    @Test
    fun `addWalletCard returns new wallet card with new id`() = runTest {
        val newCard = WalletCard(0, "Discover", "**** **** **** 0000", 500.0)
        val addedCard = repository.addWalletCard(newCard)
        // Check that id has been updated (non-zero)
        assert(addedCard.id != 0)
    }

    @Test
    fun `updateWalletCard updates an existing wallet card`() = runTest {
        val original = repository.getWalletCards().first()
        val updated = original.copy(cardName = "Updated Card")
        val result = repository.updateWalletCard(updated)
        assertEquals("Updated Card", result.cardName)
    }

    @Test(expected = Exception::class)
    fun `deleteWalletCard with invalid id throws exception`() = runTest {
        // Assuming id 999 does not exist
        repository.deleteWalletCard(999)
    }
}
