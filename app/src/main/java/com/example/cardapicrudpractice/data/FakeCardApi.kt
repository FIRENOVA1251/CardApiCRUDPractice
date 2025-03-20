package com.example.cardapicrudpractice.data

import com.example.cardapicrudpractice.domain.model.WalletCard
import kotlinx.coroutines.delay

/**
 * Fake implementation of [CardApi] to simulate network operations.
 */
class FakeWalletCardApi : CardApi {

    private val walletCards = mutableListOf(
        WalletCard(1, "Visa", "**** **** **** 1234", 1200.50),
        WalletCard(2, "MasterCard", "**** **** **** 5678", 750.00),
        WalletCard(3, "Amex", "**** **** **** 9012", 3000.00)
    )

    /**
     * Simulates a network call to retrieve wallet cards.
     */
    override suspend fun getWalletCards(): List<WalletCard> {
        delay(500) // Simulate network delay
        return walletCards.toList()
    }

    /**
     * Simulates a network call to add a wallet card.
     */
    override suspend fun addWalletCard(walletCard: WalletCard): WalletCard {
        delay(500)
        val newId = (walletCards.maxOfOrNull { it.id } ?: 0) + 1
        val newCard = walletCard.copy(id = newId)
        walletCards.add(newCard)
        return newCard
    }

    /**
     * Simulates a network call to update a wallet card.
     */
    override suspend fun updateWalletCard(id: Int, walletCard: WalletCard): WalletCard {
        delay(500)
        val index = walletCards.indexOfFirst { it.id == id }
        if (index != -1) {
            val updatedCard = walletCard.copy(id = id)
            walletCards[index] = updatedCard
            return updatedCard
        } else {
            throw Exception("Wallet card not found")
        }
    }

    /**
     * Simulates a network call to delete a wallet card.
     */
    override suspend fun deleteWalletCard(id: Int) {
        delay(500)
        val index = walletCards.indexOfFirst { it.id == id }
        if (index != -1) {
            walletCards.removeAt(index)
        } else {
            throw Exception("Wallet card not found")
        }
    }
}